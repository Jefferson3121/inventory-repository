package com.inventory.inventory_servic.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResponseProductDTO(
        Long id,
//        String sku,
        String name,
        ResponseNetContentDTO netContent,
        String nameCategory,
        BigDecimal price,
        String description,
        String brand,
        ResponseStockDTO stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}