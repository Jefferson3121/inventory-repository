package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestProductDTO(
        @NotBlank
        String name,
        @NotNull
        RequestNetContentDTO netContent,
        @NotNull
        Long categoryId,
        @NotNull
        BigDecimal price,
        String description,
        @NotBlank
        String brand

) {}
