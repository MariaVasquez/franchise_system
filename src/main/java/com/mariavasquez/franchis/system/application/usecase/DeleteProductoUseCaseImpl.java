package com.mariavasquez.franchis.system.application.usecase;

import com.mariavasquez.franchis.system.application.mapper.BranchProductMapper;
import com.mariavasquez.franchis.system.application.mapper.ProductMapper;
import com.mariavasquez.franchis.system.application.usecase.interfaces.DeleteProductoUseCase;
import com.mariavasquez.franchis.system.domain.model.Product;
import com.mariavasquez.franchis.system.domain.port.BranchProductRepository;
import com.mariavasquez.franchis.system.domain.port.ProductRepository;
import com.mariavasquez.franchis.system.infrastructure.cache.ProductCacheCleaner;
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
public class DeleteProductoUseCaseImpl implements DeleteProductoUseCase {
    private final ProductRepository productRepository;
    private final BranchProductRepository branchProductRepository;
    private final ProductCacheCleaner productCacheCleaner;

    @Override
    @Transactional
    public Mono<Void> execute(String name) {
        return validateExistProduct(name)
                .flatMap(product ->
                        productCacheCleaner.deleteAllProductPages()
                                .doOnSuccess(unused -> log.info("Eliminando productos del cache tras borrar el producto"))
                                .then(branchProductRepository.delete(product.getId()))
                                .doOnSuccess(unused -> log.info("Branch/Product con productId={} eliminada", product.getId()))
                                .then(productRepository.delete(product.getId()))
                                .doOnSuccess(unused -> log.info("Product con ID {} eliminada", product.getId()))
                )
                .doOnError(e -> log.error("Error in delete product {}", name, e))
                .onErrorMap(e -> (e instanceof CustomException) ? e : new CustomException(ResponseCode.DATABASE_ERROR));
    }

    private Mono<Product> validateExistProduct(String name){
        return productRepository.findByName(name)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.PRODUCT_NOT_FOUND)));
    }
}
