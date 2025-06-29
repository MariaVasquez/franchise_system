package com.mariavasquez.franchis.system.infrastructure.r2dbc.repository;

import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.BranchProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BranchProductReactiveRepository extends ReactiveCrudRepository<BranchProductEntity, Long> {
}
