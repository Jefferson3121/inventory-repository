package com.inventory.inventory_servic.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record RequestUpdateSupplierDTO(
        String name,
        String contactName,
        String phone,
        @Email(message = "El email debe tener un formato válido")
        String email,
        @Valid
        RequestAddressDTO address,
        Boolean active
) {
}
