package com.inventory.inventory_servic.application.dto.request;

import com.inventory.inventory_servic.domain.model.ProductType;
import jakarta.validation.constraints.NotNull;

public record RequestChangeTypeDTO(@NotNull ProductType type) {
}
