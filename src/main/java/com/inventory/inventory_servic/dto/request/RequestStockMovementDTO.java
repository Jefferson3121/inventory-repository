package com.inventory.inventory_servic.dto.request;


import com.inventory.inventory_servic.domain.MovementReason;
import com.inventory.inventory_servic.domain.MovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RequestStockMovementDTO(
        @NotNull(message = "El lote es obligatorio")
        String batchId,
        @NotNull(message = "El tipo de movimiento es obligatorio")
        MovementType type,
        @NotNull(message = "El motivo es obligatorio")
        MovementReason reason,
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a cero")
        BigDecimal quantity,
        String notes
) {}