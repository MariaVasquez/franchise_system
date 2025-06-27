package com.mariavasquez.franchis.system.infrastructure.web.controller;

import com.mariavasquez.franchis.system.application.service.interfaces.CreateFranchiseUseCase;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.FranchiseRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.FranchiseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final CreateFranchiseUseCase createFranchiseUseCase;

    @PostMapping
    public Mono<ResponseEntity<FranchiseResponseDto>> create(@RequestBody FranchiseRequestDto request) {
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).build());
    }
}
