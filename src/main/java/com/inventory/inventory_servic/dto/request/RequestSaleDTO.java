package com.inventory.inventory_servic.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record RequestSaleDTO(
        @NotNull(message = "El monto cobrado es obligatorio")
        @PositiveOrZero(message = "El monto cobrado no puede ser negativo")
        BigDecimal amountCollected,
        @NotNull(message = "la fecha en que se realizo la venta esta vacia, debe incluir la fecha de venta")
        LocalDateTime saleDate,
        @NotEmpty(message = "La venta debe incluir al menos un producto")
        @Valid
        List<RequestSaleDetailDTO> details
) {}
