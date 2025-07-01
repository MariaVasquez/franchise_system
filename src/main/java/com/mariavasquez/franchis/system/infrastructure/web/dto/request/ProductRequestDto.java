package com.mariavasquez.franchis.system.infrastructure.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "DTO para la creación de un producto en una sucursal")
public class ProductRequestDto {

    @Schema(description = "Nombre del producto", example = "Café Americano")
    private String name;

    @Schema(description = "Descripción del producto", example = "Café sin leche de 8 oz")
    private String description;

    @Schema(description = "Precio del producto", example = "8500.00")
    private BigDecimal price;

    @Schema(description = "Cantidad de stock que se asignará en la sucursal", example = "50")
    private Integer stock;
}
