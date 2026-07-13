package com.inventory.inventory_servic.repository;

import com.inventory.inventory_servic.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByName(String name);
}
