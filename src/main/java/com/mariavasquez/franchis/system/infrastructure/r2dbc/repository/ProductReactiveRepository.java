package com.mariavasquez.franchis.system.infrastructure.r2dbc.repository;

import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.ProductEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductReactiveRepository extends ReactiveCrudRepository<ProductEntity, Long> {
    Mono<ProductEntity> findByName(String name);
    @Query("SELECT * FROM product ORDER BY id LIMIT :limit OFFSET :offset")
    Flux<ProductEntity> findAllByPage(@Param("offset") int offset, @Param("limit") int limit);
}
