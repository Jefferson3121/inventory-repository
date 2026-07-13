package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotNull;

public record RequestStockDTO(
        @NotNull(message = "El valor para la propiedad 'quantity' en StockDTO no debe estar vacio")
        Long quantity,
        @NotNull(message = "El valor para la propiedad 'minQuantity' en StockDTO no debe estar vacio")
        Long minQuantity) {
}
