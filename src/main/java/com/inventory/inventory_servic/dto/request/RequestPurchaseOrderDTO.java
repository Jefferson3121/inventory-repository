package com.inventory.inventory_servic.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record RequestPurchaseOrderDTO(
        @NotNull(message = "El proveedor es obligatorio")
        Long supplierId,
        @NotNull(message = "La fecha de la orden es obligatoria")
        LocalDate orderDate,
        @NotEmpty(message = "La orden debe incluir al menos un producto")
        @Valid
        List<RequestPurchaseDetailDTO> details
) {}