package com.inventory.inventory_servic.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestSupplierDTO(
        @NotBlank(message = "El nombre es obligatorio")
        String name,
        String contactName,
        @NotBlank(message = "El teléfono es obligatorio")
        String phone,
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe tener un formato válido")
        String email,
        @NotNull(message = "La dirección es obligatoria")
        @Valid
        RequestAddressDTO address) {
}
