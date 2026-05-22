package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestProductDTO(
        @NotBlank
        String name,
        String description,
        @NotBlank
        String brand,
        @NotNull
        BigDecimal price,
        @NotNull
        Long categoryId,
        @NotNull
        RequestNetContentDTO measurement
) {}
