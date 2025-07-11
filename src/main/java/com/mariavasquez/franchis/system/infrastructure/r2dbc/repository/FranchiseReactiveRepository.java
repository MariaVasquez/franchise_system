package com.mariavasquez.franchis.system.infrastructure.r2dbc.repository;

import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FranchiseReactiveRepository extends ReactiveCrudRepository<FranchiseEntity, Long> {
    Mono<FranchiseEntity> findByName(String name);
}
