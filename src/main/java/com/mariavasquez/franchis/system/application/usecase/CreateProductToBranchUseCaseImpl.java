package com.mariavasquez.franchis.system.application.usecase;

import com.mariavasquez.franchis.system.application.mapper.ProductMapper;
import com.mariavasquez.franchis.system.application.usecase.interfaces.CreateProductToBranchUseCase;
import com.mariavasquez.franchis.system.domain.model.Branch;
import com.mariavasquez.franchis.system.domain.model.BranchProduct;
import com.mariavasquez.franchis.system.domain.model.Product;
import com.mariavasquez.franchis.system.domain.port.BranchProductRepository;
import com.mariavasquez.franchis.system.domain.port.BranchRepository;
import com.mariavasquez.franchis.system.domain.port.ProductRepository;
import com.mariavasquez.franchis.system.domain.port.RedisCacheService;
import com.mariavasquez.franchis.system.infrastructure.cache.ProductCacheCleaner;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.ProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateProductToBranchUseCaseImpl implements CreateProductToBranchUseCase {
    private final ProductMapper mapper;
    private final BranchRepository branchRepository;
    private final BranchProductRepository branchProductRepository;
    private final ProductRepository productRepository;
    private final ProductCacheCleaner productCacheCleaner;

    @Override
    @Transactional
    public Mono<ProductResponseDto> execute(ProductRequestDto productRequestDto, String branchName) {
        return createProductForBranch(branchName, productRequestDto);
    }

    public Mono<ProductResponseDto> createProductForBranch(String branchName, ProductRequestDto dto) {
        return validateExistBranch(branchName)
                .flatMap(branch -> {
                    Product product = buildProductFromDto(dto, branch.getFranchiseId());
                    return saveProductAndAssignToBranch(product, branch.getId(), dto.getStock())
                            .flatMap(savedProduct ->
                                    productCacheCleaner.deleteAllProductPages().thenReturn(savedProduct)
                            );
                })
                .doOnError(e -> log.error("Error in create product {}", dto.getName(), e))
                .onErrorMap(e -> (e instanceof CustomException) ? e : new CustomException(ResponseCode.DATABASE_ERROR));
    }

    private Product buildProductFromDto(ProductRequestDto dto, Long franchiseId) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .franchiseId(franchiseId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private Mono<ProductResponseDto> saveProductAndAssignToBranch(Product product, Long branchId, Integer stock) {
        return validateExistProduct(product.getName()).then(productRepository.save(product))
                .flatMap(savedProduct -> {
                    BranchProduct branchProduct = BranchProduct.builder()
                            .branchId(branchId)
                            .productId(savedProduct.getId())
                            .stock(stock)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();

                    return branchProductRepository.save(branchProduct)
                            .map(bp -> ProductResponseDto.builder()
                                    .id(savedProduct.getId())
                                    .name(savedProduct.getName())
                                    .description(savedProduct.getDescription())
                                    .price(savedProduct.getPrice())
                                    .stock(bp.getStock())
                                    .branchId(bp.getBranchId())
                                    .franchiseId(savedProduct.getFranchiseId())
                                    .createdAt(savedProduct.getCreatedAt())
                                    .updatedAt(savedProduct.getUpdatedAt())
                                    .build()
                            );
                });
    }

    private Mono<Product> validateExistProduct(String name) {
        return Mono.defer(() ->
                productRepository.findByName(name)
                        .hasElement()
                        .flatMap(exists -> Boolean.TRUE.equals(exists)
                                ? Mono.error(new CustomException(ResponseCode.PRODUCT_EXIST, name))
                                : Mono.empty()
                        )
        );
    }

    private Mono<Branch> validateExistBranch(String branchName) {
        return branchRepository.findByName(branchName)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.BRANCH_NOT_FOUND)));
    }
}
