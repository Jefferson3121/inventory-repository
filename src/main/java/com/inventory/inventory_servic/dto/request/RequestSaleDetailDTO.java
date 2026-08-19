package com.inventory.inventory_servic.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record RequestSaleDetailDTO(
        @NotNull(message = "El producto es obligatorio")
        Long productId,
        @NotNull(message = "El lote es obligatorio")
        String batchCode,
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a cero")
        BigDecimal quantity,
        @NotNull(message = "El precio unitario es obligatorio")
        @PositiveOrZero(message = "El precio unitario no puede ser negativo")
        BigDecimal unitPrice
) {}
