package com.inventory.inventory_servic.dto.request;


import jakarta.validation.constraints.NotBlank;

public record RequestAddressDTO(
        @NotBlank(message = "La calle es obligatoria")
        String street,
        @NotBlank(message = "La ciudad es obligatoria")
        String city,
        @NotBlank(message = "El estado/departamento es obligatorio")
        String state,
        @NotBlank(message = "El código postal es obligatorio")
        String zipCode,
        @NotBlank(message = "El país es obligatorio")
        String country
) {}