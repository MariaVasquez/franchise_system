package com.mariavasquez.franchis.system.application.usecase.interfaces;

import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductPageResponseDto;
import reactor.core.publisher.Mono;

public interface GetAllProductsUseCase {
    Mono<ProductPageResponseDto> execute(int page, int size);
}
