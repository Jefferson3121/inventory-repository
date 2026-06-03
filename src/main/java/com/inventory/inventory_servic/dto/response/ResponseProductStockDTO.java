package com.inventory.inventory_servic.dto.response;

public record ResponseProductStockDTO(String name, long quantity, long minQuantity) {
}
