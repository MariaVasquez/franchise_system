package com.mariavasquez.franchis.system.application.usecase.interfaces;

import com.mariavasquez.franchis.system.infrastructure.web.dto.request.ProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import reactor.core.publisher.Mono;

public interface CreateProductToBranchUseCase {
    Mono<ProductResponseDto> execute(ProductRequestDto productRequestDto, String branchName);
}
