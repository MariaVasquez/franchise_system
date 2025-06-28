package com.mariavasquez.franchis.system.infrastructure.r2dbc.repository;

import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.BranchEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BranchReactiveRepository extends ReactiveCrudRepository<BranchEntity, Long> {
    Mono<BranchEntity> findByName(String name);
}
