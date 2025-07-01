package com.mariavasquez.franchis.system.infrastructure.r2dbc.service;

import com.mariavasquez.franchis.system.application.mapper.BranchMapper;
import com.mariavasquez.franchis.system.domain.model.Branch;
import com.mariavasquez.franchis.system.domain.port.BranchRepository;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.repository.BranchReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BranchRepositoryAdapter implements BranchRepository {
    private final BranchReactiveRepository branchReactiveRepository;
    private final BranchMapper mapper;

    @Override
    public Mono<Branch> save(Branch branch) {
        return branchReactiveRepository.save(mapper.toEntity(branch))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Branch> findByName(String name) {
        return branchReactiveRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return branchReactiveRepository.deleteById(id);
    }
}
