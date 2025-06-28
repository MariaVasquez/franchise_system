package com.mariavasquez.franchis.system.infrastructure.web.controller;

import com.mariavasquez.franchis.system.application.usecase.interfaces.CreateBranchToFranchiseUseCase;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.BranchRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.BranchResponseDto;
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
@RequestMapping("/api/branches")
@Tag(name = "Sucursales", description = "Operaciones relacionadas con sucursales")
public class BranchController {

    private final CreateBranchToFranchiseUseCase createBranchToFranchiseUseCase;

    @Operation(
            summary = "Crear una nueva sucursal",
            description = "Este endpoint permite crear una nueva sucursal y asociarla a una franquicia existente.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sucursal creada exitosamente",
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
    public Mono<GenericResponseDto<BranchResponseDto>> createFranchise(
            @org.springframework.web.bind.annotation.RequestBody
            @Valid
            @RequestBody(
                    description = "Datos necesarios para crear una sucursal",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BranchRequestDto.class))
            )
            BranchRequestDto requestDto
    ) {
        return createBranchToFranchiseUseCase.execute(requestDto)
                .map(data -> new GenericResponseDto<>(ResponseCode.TRANSACTION_CREATED, data));
    }
}
