package com.mariavasquez.franchis.system.application.usecase.interfaces;

import com.mariavasquez.franchis.system.infrastructure.web.dto.request.FranchiseRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.FranchiseResponseDto;
import reactor.core.publisher.Mono;

public interface CreateFranchiseUseCase {
    Mono<FranchiseResponseDto> execute(FranchiseRequestDto franchiseRequestDto);
}
