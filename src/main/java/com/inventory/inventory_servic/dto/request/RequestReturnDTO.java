package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.domain.ReturnReason;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RequestReturnDTO(
        @NotNull(message = "El detalle de venta es obligatorio")
        Long saleDetailId,
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad a devolver debe ser mayor a cero")
        Long quantity,
        @NotNull(message = "El motivo es obligatorio")
        ReturnReason reason,
        String notes
) {}
