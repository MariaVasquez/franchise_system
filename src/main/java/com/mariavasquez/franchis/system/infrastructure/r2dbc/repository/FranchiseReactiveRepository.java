package com.mariavasquez.franchis.system.infrastructure.r2dbc.repository;

import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FranchiseReactiveRepository extends ReactiveCrudRepository<FranchiseEntity, Long> {
}
