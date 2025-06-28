package com.mariavasquez.franchis.system.infrastructure.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO para crear una sucursal (branch) asociada a una franquicia")
public class BranchRequestDto {

    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Schema(description = "Nombre de la sucursal", example = "Sucursal Centro", required = true)
    private String name;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    @Schema(description = "Dirección de la sucursal", example = "Cra 45 #10-15, Medellín", required = true)
    private String address;

    @NotNull(message = "El ID de la franquicia es obligatorio")
    @Schema(description = "ID de la franquicia a la que pertenece esta sucursal", example = "1", required = true)
    private Long franchiseId;
}
