package com.mariavasquez.franchis.system.domain.port;

import com.mariavasquez.franchis.system.domain.model.Branch;
import reactor.core.publisher.Mono;

public interface BranchRepository {
    Mono<Branch> save(Branch branch);
    Mono<Branch> findByName(String name);
}
