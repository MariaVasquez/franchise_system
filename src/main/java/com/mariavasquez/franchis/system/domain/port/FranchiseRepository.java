package com.mariavasquez.franchis.system.domain.port;

import com.mariavasquez.franchis.system.domain.model.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> save(Franchise franchise);
    Mono<Franchise> findByName(String name);
    Mono<Franchise> findById(Long id);
    Flux<Franchise> findAll();
}
