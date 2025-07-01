package com.mariavasquez.franchis.system.application.usecase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mariavasquez.franchis.system.application.mapper.ProductMapper;
import com.mariavasquez.franchis.system.application.usecase.interfaces.GetAllProductsUseCase;
import com.mariavasquez.franchis.system.domain.port.BranchProductRepository;
import com.mariavasquez.franchis.system.domain.port.ProductRepository;
import com.mariavasquez.franchis.system.domain.port.RedisCacheService;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductPageResponseDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {
    private final ProductRepository productRepository;
    private final BranchProductRepository branchProductRepository;
    private final RedisCacheService redisCacheService;
    private final ProductMapper productMapper;

    @Override
    public Mono<ProductPageResponseDto> execute(int page, int size) {
        String cacheKey = buildCacheKey(page, size);

        return redisCacheService.get(cacheKey, new TypeReference<List<ProductResponseDto>>() {})
                .switchIfEmpty(Mono.defer(() -> loadAndCacheProducts(page, size, cacheKey)))
                .flatMap(products -> buildPageResponse(products, page, size));
    }

    private String buildCacheKey(int page, int size) {
        return String.format("products::page:%d_size:%d", page, size);
    }

    private Mono<List<ProductResponseDto>> loadAndCacheProducts(int page, int size, String cacheKey) {
        return productRepository.findAll(page, size)
                .map(productMapper::toDto)
                .collectList()
                .flatMap(this::enrichWithBranchIds)
                .flatMap(products -> redisCacheService.set(cacheKey, products).thenReturn(products));
    }

    private Mono<List<ProductResponseDto>> enrichWithBranchIds(List<ProductResponseDto> products) {
        return Flux.fromIterable(products)
                .flatMap(product -> branchProductRepository.findByProdyctId(product.getId())
                        .doOnNext(bp -> {
                            product.setBranchId(bp.getBranchId());
                            product.setStock(bp.getStock());
                        })
                        .thenReturn(product)
                )
                .collectList();
    }

    private Mono<ProductPageResponseDto> buildPageResponse(List<ProductResponseDto> products, int page, int size) {
        return productRepository.getTotalProduct()
                .map(total -> ProductPageResponseDto.builder()
                        .products(products)
                        .page(page)
                        .size(size)
                        .total(total)
                        .build());
    }
}
