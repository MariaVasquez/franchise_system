package com.mariavasquez.franchis.system.infrastructure.r2dbc.repository;

import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductReactiveRepository extends ReactiveCrudRepository<ProductEntity, Long> {
    Mono<ProductEntity> findByName(String name);
}
