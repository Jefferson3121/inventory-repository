package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;


public record RequestBatchDTO(
        @NotNull(message = "El producto es obligatorio")
        Long productId,
        @NotNull(message = "La fecha de vencimiento es obligatoria")
        LocalDate expirationDate,
        @NotNull(message = "La fecha de fabricación es obligatoria")
        LocalDate manufacturingDate,
        @NotNull(message = "La cantidad inicial es obligatoria")
        @Positive(message = "La cantidad inicial debe ser mayor a cero")
        BigDecimal quantity
) {}
