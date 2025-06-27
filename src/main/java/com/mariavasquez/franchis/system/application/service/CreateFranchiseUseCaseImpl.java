package com.mariavasquez.franchis.system.application.service;

import com.mariavasquez.franchis.system.application.service.interfaces.CreateFranchiseUseCase;
import com.mariavasquez.franchis.system.domain.model.Franchise;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.FranchiseRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateFranchiseUseCaseImpl implements CreateFranchiseUseCase {
    @Override
    public Mono<Franchise> execute(FranchiseRequestDto franchiseRequestDto) {
        return null;
    }
}
