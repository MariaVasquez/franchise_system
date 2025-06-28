package com.mariavasquez.franchis.system.infrastructure.r2dbc.service;

import com.mariavasquez.franchis.system.application.mapper.FranchiseMapper;
import com.mariavasquez.franchis.system.domain.model.Franchise;
import com.mariavasquez.franchis.system.domain.port.FranchiseRepository;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.FranchiseEntity;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.repository.FranchiseReactiveRepository;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class FranchiseRepositoryAdapter implements FranchiseRepository {
    private final FranchiseReactiveRepository franchiseReactiveRepository;
    private final FranchiseMapper mapper;

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return franchiseReactiveRepository.save(mapper.toEntity(franchise))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Franchise> findByName(String name) {
        return franchiseReactiveRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Franchise> findById(Long id) {
        return franchiseReactiveRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Franchise> findAll() {
        return franchiseReactiveRepository.findAll()
                .map(mapper::toDomain);
    }
}
