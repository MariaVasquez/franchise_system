package com.mariavasquez.franchis.system.application.usecase;

import com.mariavasquez.franchis.system.application.usecase.interfaces.UpdateProductStockUseCase;
import com.mariavasquez.franchis.system.domain.model.BranchProduct;
import com.mariavasquez.franchis.system.domain.model.Product;
import com.mariavasquez.franchis.system.domain.port.BranchProductRepository;
import com.mariavasquez.franchis.system.domain.port.ProductRepository;
import com.mariavasquez.franchis.system.infrastructure.cache.ProductCacheCleaner;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.UpdateProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateProductStockUseCaseImpl implements UpdateProductStockUseCase {
    private final ProductRepository productRepository;
    private final BranchProductRepository branchProductRepository;
    private final ProductCacheCleaner productCacheCleaner;

    @Override
    @Transactional
    public Mono<ProductResponseDto> execute(String name, UpdateProductRequestDto request) {
        return validateExistProduct(name)
                .flatMap(product -> updateBranchProductStock(product, request.getStock()))
                .flatMap(this::clearCacheAndReturn)
                .doOnError(e -> log.error("Error in update stock product {}", name, e))
                .onErrorMap(e -> (e instanceof CustomException) ? e : new CustomException(ResponseCode.DATABASE_ERROR));
    }

    private Mono<ProductResponseDto> updateBranchProductStock(Product product, int stock) {
        return branchProductRepository.findByProdyctId(product.getId())
                .flatMap(branchProduct -> {
                    branchProduct.setStock(stock);
                    return branchProductRepository.save(branchProduct)
                            .map(saved -> buildProductResponseDto(product, saved));
                });
    }

    private ProductResponseDto buildProductResponseDto(Product product, BranchProduct bp) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(bp.getStock())
                .branchId(bp.getBranchId())
                .franchiseId(product.getFranchiseId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private Mono<ProductResponseDto> clearCacheAndReturn(ProductResponseDto dto) {
        return productCacheCleaner.deleteAllProductPages()
                .thenReturn(dto);
    }

    private Mono<Product> validateExistProduct(String name) {
        return productRepository.findByName(name)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.PRODUCT_NOT_FOUND)));
    }
}
