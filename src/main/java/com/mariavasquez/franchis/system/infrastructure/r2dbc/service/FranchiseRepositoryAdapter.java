package com.mariavasquez.franchis.system.infrastructure.r2dbc.service;

import com.mariavasquez.franchis.system.domain.model.Franchise;
import com.mariavasquez.franchis.system.domain.port.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseRepositoryAdapter implements FranchiseRepository {
    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return null;
    }

    @Override
    public Mono<Franchise> findById(String id) {
        return null;
    }

    @Override
    public Flux<Franchise> findAll() {
        return null;
    }
}
