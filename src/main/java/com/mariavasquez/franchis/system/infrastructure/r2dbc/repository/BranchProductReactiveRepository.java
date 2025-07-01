package com.mariavasquez.franchis.system.infrastructure.r2dbc.repository;

import com.mariavasquez.franchis.system.domain.model.BranchProduct;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.BranchProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BranchProductReactiveRepository extends ReactiveCrudRepository<BranchProductEntity, Long> {
    Mono<Void> deleteByProductId(Long productId);
    Mono<BranchProductEntity> findByProductId (Long productId);
    Mono<BranchProduct> findTopByBranchIdOrderByStockDesc(String branchId);
}
