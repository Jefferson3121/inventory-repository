package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.domain.Category;

public record RequestUpdateCategory(
        String description,
        Category parentCategory,
        boolean active) {
}
