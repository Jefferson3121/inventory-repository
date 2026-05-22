package com.inventory.inventory_servic.repository;

import com.inventory.inventory_servic.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
