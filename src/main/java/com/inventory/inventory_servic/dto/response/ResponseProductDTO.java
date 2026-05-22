package com.inventory.inventory_servic.dto.response;

import java.time.LocalDateTime;

public record ResponseProductDTO(
        Long id,
        String name,
        String description,
        ResponseCategoryDTO parentCategory,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}