package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestCategoryDTO(

        @NotBlank
        String name,
        String description,
        long idParentCategory,
        boolean active) {
}
