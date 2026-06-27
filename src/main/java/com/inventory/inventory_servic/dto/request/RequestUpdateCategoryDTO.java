package com.inventory.inventory_servic.dto.request;

public record RequestUpdateCategoryDTO(
        String name,
        String description,
        long parentCategoryId,
        boolean active) {
}
