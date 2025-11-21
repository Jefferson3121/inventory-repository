package com.inventory.inventory_servic.dto;

import com.inventory.inventory_servic.model.Category;
import com.inventory.inventory_servic.model.ProductType;
import com.inventory.inventory_servic.model.Unit;

import java.math.BigDecimal;

public record RequestProductDTO(String name, String brand, Unit unit, BigDecimal netContent, BigDecimal price, String category, ProductType type) {
}
