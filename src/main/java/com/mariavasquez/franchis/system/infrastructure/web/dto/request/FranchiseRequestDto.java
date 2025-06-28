package com.mariavasquez.franchis.system.infrastructure.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO para registrar una nueva franquicia")
public class FranchiseRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Schema(description = "Nombre de la franquicia", example = "Franquicia Don Arepa", required = true)
    private String name;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Schema(description = "Descripción de la franquicia", example = "Franquicia especializada en comida típica colombiana")
    private String description;

    @NotBlank(message = "El nombre del propietario es obligatorio")
    @Size(max = 100, message = "El nombre del propietario no puede superar los 100 caracteres")
    @Schema(description = "Nombre del propietario de la franquicia", example = "Juan Pérez", required = true)
    private String ownerName;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Schema(description = "Correo electrónico de contacto", example = "contacto@donarepa.com", required = true)
    private String email;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^[0-9\\-\\+]{7,15}$", message = "Número de teléfono no válido")
    @Schema(description = "Número de teléfono de contacto", example = "+57 3001234567", required = true)
    private String phone;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    @Schema(description = "Dirección principal de la franquicia", example = "Av. 1 de Mayo #20-15, Bogotá", required = true)
    private String address;
}
