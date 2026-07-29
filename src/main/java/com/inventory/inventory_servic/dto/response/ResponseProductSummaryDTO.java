package com.inventory.inventory_servic.dto.response;

public record ResponseProductSummaryDTO(
        long id,
        String name,
        String brand
) {
}
