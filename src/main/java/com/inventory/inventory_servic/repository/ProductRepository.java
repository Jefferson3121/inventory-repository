package com.inventory.inventory_servic.repository;

import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.domain.UnitMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameAndBrandAndNetContent_ValueAndNetContent_Unit(
            String name, String brand, BigDecimal value, UnitMeasurement unit);
}
