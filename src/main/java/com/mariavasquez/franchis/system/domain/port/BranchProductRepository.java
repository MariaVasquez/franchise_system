package com.mariavasquez.franchis.system.domain.port;

import com.mariavasquez.franchis.system.domain.model.BranchProduct;
import reactor.core.publisher.Mono;

public interface BranchProductRepository {
    Mono<BranchProduct> save(BranchProduct branchProduct);
    Mono<BranchProduct> findById(Long id);
    Mono<BranchProduct> findByProdyctId(Long id);
    Mono<Void> delete(Long id);
}
