package com.mariavasquez.franchis.system.application.usecase.interfaces;

import reactor.core.publisher.Mono;

public interface DeleteProductoUseCase {
    Mono<Void> execute(String name);
}
