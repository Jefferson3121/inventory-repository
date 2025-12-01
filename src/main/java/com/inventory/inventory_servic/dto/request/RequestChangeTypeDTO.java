package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.model.ProductType;
import jakarta.validation.constraints.NotNull;

public record RequestChangeTypeDTO(@NotNull ProductType type) {
}
