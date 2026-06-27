package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.domain.Category;

import java.math.BigDecimal;

public record RequestUpdateProductDTO(
        String name,
        String description,
        long categoryId,
        String brand,
        BigDecimal price,
        RequestNetContentDTO netContent) {
}
