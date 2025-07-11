package com.mariavasquez.franchis.system.infrastructure.web.controller;

import com.mariavasquez.franchis.system.application.usecase.interfaces.CreateFranchiseUseCase;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.FranchiseRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.FranchiseResponseDto;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.dto.GenericResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/franchises")
@Tag(name = "Franquicias", description = "Operaciones relacionadas con franquicias")
public class FranchiseController {

    private final CreateFranchiseUseCase createFranchiseUseCase;

    @Operation(
            summary = "Crear una nueva franquicia",
            description = "Este endpoint permite registrar una nueva franquicia con sus datos principales.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Franquicia creada exitosamente",
                            content = @Content(schema = @Schema(implementation = GenericResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public Mono<GenericResponseDto<FranchiseResponseDto>> createFranchise(
            @org.springframework.web.bind.annotation.RequestBody
            @Valid
            @RequestBody(
                    description = "Datos necesarios para registrar una franquicia",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FranchiseRequestDto.class))
            )
            FranchiseRequestDto requestDto
    ) {
        return createFranchiseUseCase.execute(requestDto)
                .map(data -> new GenericResponseDto<>(ResponseCode.TRANSACTION_CREATED, data));
    }
}
