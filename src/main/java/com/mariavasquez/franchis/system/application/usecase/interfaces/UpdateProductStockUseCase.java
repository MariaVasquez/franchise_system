package com.mariavasquez.franchis.system.application.usecase.interfaces;

import com.mariavasquez.franchis.system.infrastructure.web.dto.request.UpdateProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import reactor.core.publisher.Mono;

public interface UpdateProductStockUseCase {
    Mono<ProductResponseDto> execute(String name, UpdateProductRequestDto request);
}
