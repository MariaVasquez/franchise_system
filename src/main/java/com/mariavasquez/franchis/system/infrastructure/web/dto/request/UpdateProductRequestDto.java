package com.mariavasquez.franchis.system.infrastructure.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDto {
    @Schema(description = "Cantidad de stock que se asignará en la sucursal", example = "50")
    @NotNull(message = "El campo stock no puede ser nulo")
    private Integer stock;
}
