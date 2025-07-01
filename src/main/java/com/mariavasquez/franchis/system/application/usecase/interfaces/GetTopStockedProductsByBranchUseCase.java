package com.mariavasquez.franchis.system.application.usecase.interfaces;

import reactor.core.publisher.Mono;

public interface GetTopStockedProductsByBranchUseCase {
    Mono<Void> execute();
}
