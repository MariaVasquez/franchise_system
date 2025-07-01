package com.mariavasquez.franchis.system.domain.port;

import com.mariavasquez.franchis.system.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> save(Product franchise);
    Mono<Product> findByName(String name);
    Mono<Product> findById(Long id);
    Mono<Void> delete(Long id);
    Flux<Product> findAll(int page, int size);
    Mono<Long> getTotalProduct();
}