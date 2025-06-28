package com.mariavasquez.franchis.system.application.usecase.interfaces;

import com.mariavasquez.franchis.system.infrastructure.web.dto.request.BranchRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.BranchResponseDto;
import reactor.core.publisher.Mono;

public interface CreateBranchToFranchiseUseCase {
    Mono<BranchResponseDto> execute(BranchRequestDto branchRequestDto);
}
