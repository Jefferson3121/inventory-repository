package com.inventory.inventory_servic.dto.response;

import com.inventory.inventory_servic.model.Category;
import com.inventory.inventory_servic.model.ProductType;
import com.inventory.inventory_servic.model.Unit;

import java.math.BigDecimal;

public record ResponseProductDTO(long id, String name, String brand, Unit unit, BigDecimal netContent, BigDecimal price, Category category, ProductType type) {
}
