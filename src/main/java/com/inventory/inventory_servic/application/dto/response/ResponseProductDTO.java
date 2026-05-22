package com.inventory.inventory_servic.application.dto.response;

import com.inventory.inventory_servic.domain.model.Category;
import com.inventory.inventory_servic.domain.model.ProductType;
import com.inventory.inventory_servic.domain.model.Unit;

import java.math.BigDecimal;

public record ResponseProductDTO(long id, String name, String brand, Unit unit, BigDecimal netContent, BigDecimal price, Category category, ProductType type) {
}
