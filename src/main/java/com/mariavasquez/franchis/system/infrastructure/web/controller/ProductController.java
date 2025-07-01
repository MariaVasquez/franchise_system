package com.mariavasquez.franchis.system.infrastructure.web.controller;

import com.mariavasquez.franchis.system.application.usecase.interfaces.CreateProductToBranchUseCase;
import com.mariavasquez.franchis.system.application.usecase.interfaces.DeleteProductoUseCase;
import com.mariavasquez.franchis.system.application.usecase.interfaces.GetAllProductsUseCase;
import com.mariavasquez.franchis.system.application.usecase.interfaces.UpdateProductStockUseCase;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.ProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.UpdateProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductPageResponseDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.dto.GenericResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
public class ProductController {
    private final CreateProductToBranchUseCase createProductToBranchUseCase;
    private final DeleteProductoUseCase deleteProductoUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;

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

    @Operation(
            summary = "Actualizar stock de un producto",
            description = "Permite actualizar el stock de un producto existente mediante su nombre",
            parameters = {
                    @Parameter(name = "name", description = "Nombre del producto", required = true)
            },
            requestBody = @RequestBody(
                    description = "Nuevo stock del producto",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UpdateProductRequestDto.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente",
                            content = @Content(schema = @Schema(implementation = GenericResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @PutMapping("/{name}/stock")
    public Mono<GenericResponseDto<ProductResponseDto>> updateStock(
            @PathVariable String name,
            @Valid @org.springframework.web.bind.annotation.RequestBody UpdateProductRequestDto request
    ) {
        return updateProductStockUseCase.execute(name, request)
                .map(dto -> new GenericResponseDto<>(ResponseCode.TRANSACTION_SUCCESS, dto));
    }


    @Operation(
            summary = "Eliminar un producto por nombre",
            description = "Elimina un producto del sistema junto con su relación con la sucursal.",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Nombre del producto a eliminar",
                            required = true,
                            example = "Café Americano"
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @DeleteMapping("/{name}")
    public Mono<GenericResponseDto<Void>> deleteProduct(@PathVariable String name) {
        return deleteProductoUseCase.execute(name)
                .then(Mono.just(new GenericResponseDto<>(ResponseCode.TRANSACTION_SUCCESS, null)));
    }

    @Operation(
            summary = "Listar productos paginados",
            description = "Obtiene una lista de productos paginados desde Redis o base de datos.",
            parameters = {
                    @Parameter(name = "page", description = "Número de página (empezando desde 0)", example = "0"),
                    @Parameter(name = "size", description = "Tamaño de la página", example = "10")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de productos paginada",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductPageResponseDto.class)))
                    )
            }
    )
    @GetMapping
    public Mono<GenericResponseDto<ProductPageResponseDto>> getAllProducts(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return getAllProductsUseCase.execute(page, size)
                .map(data -> new GenericResponseDto<>(ResponseCode.TRANSACTION_SUCCESS, data));
    }
}
