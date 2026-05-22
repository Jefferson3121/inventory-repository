package com.inventory.inventory_servic.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCategoryDTO(@NotBlank String name,@NotBlank String description,@NotNull Integer idCategoryParent) {
}
