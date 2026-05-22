package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.domain.Category;

import java.math.BigDecimal;

public record RequestUpdateProductDTO(
        String name,
        String description,
        Category category,
        String brand,
        BigDecimal price) {
}
