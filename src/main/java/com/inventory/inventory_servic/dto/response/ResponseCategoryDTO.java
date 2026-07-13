package com.inventory.inventory_servic.dto.response;

import java.time.LocalDateTime;

public record ResponseCategoryDTO(
        long id,
        String name,
        String description,
        Long parentCategoryId,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updateAt


) {
}
