package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestCategoryDTO(@NotBlank String name,@NotBlank String description) {
}
