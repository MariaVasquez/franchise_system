package com.mariavasquez.franchis.system.application.service.interfaces;

import com.mariavasquez.franchis.system.domain.model.Franchise;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.FranchiseRequestDto;
import reactor.core.publisher.Mono;

public interface CreateFranchiseUseCase {
    Mono<Franchise> execute(FranchiseRequestDto franchiseRequestDto);
}
