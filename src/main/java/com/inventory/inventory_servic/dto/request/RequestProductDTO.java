package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.model.ProductType;
import com.inventory.inventory_servic.model.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestProductDTO(@NotBlank String name, @NotBlank String brand, @NotNull Unit unit, @NotNull BigDecimal netContent,@NotNull BigDecimal price,@NotBlank String category,@NotNull ProductType type) {
}
