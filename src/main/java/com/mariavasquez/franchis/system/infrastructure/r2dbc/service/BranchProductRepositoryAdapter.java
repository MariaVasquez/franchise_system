package com.mariavasquez.franchis.system.infrastructure.r2dbc.service;

import com.mariavasquez.franchis.system.application.mapper.BranchProductMapper;
import com.mariavasquez.franchis.system.domain.model.BranchProduct;
import com.mariavasquez.franchis.system.domain.port.BranchProductRepository;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.repository.BranchProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BranchProductRepositoryAdapter implements BranchProductRepository {
    private final BranchProductMapper mapper;
    private final BranchProductReactiveRepository branchProductReactiveRepository;

    @Override
    public Mono<BranchProduct> save(BranchProduct branchProduct) {
        return branchProductReactiveRepository.save(mapper.toEntity(branchProduct))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<BranchProduct> findById(Long id) {
        return branchProductReactiveRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<BranchProduct> findByProdyctId(Long id) {
        return branchProductReactiveRepository.findByProductId(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return branchProductReactiveRepository.deleteByProductId(id);
    }
}
