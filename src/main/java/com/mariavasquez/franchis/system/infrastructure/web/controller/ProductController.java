package com.mariavasquez.franchis.system.infrastructure.web.controller;

import com.mariavasquez.franchis.system.application.usecase.interfaces.CreateProductToBranchUseCase;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.ProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.dto.GenericResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
public class ProductController {
    private final CreateProductToBranchUseCase createProductToBranchUseCase;

    @Operation(
            summary = "Crear un nuevo Producto en una sucursal",
            description = "Registra un nuevo producto y lo asocia a una sucursal específica mediante su nombre.",
            parameters = {
                    @Parameter(
                            name = "branchName",
                            description = "Nombre de la sucursal a la que se asociará el producto",
                            required = true,
                            example = "Sucursal Norte"
                    )
            },
            requestBody = @RequestBody(
                    description = "Datos necesarios para registrar un Producto",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProductRequestDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Producto creado exitosamente",
                            content = @Content(schema = @Schema(implementation = GenericResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida",
                            content = @Content
                    )
            }
    )
    @PostMapping("/{branchName}")
    public Mono<GenericResponseDto<ProductResponseDto>> createProduct(
            @PathVariable String branchName,
            @Valid @org.springframework.web.bind.annotation.RequestBody ProductRequestDto requestDto
    ) {
        return createProductToBranchUseCase.execute(requestDto, branchName)
                .map(data -> new GenericResponseDto<>(ResponseCode.TRANSACTION_CREATED, data));
    }
}
