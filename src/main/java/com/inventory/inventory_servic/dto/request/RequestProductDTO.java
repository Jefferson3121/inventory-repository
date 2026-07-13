package com.inventory.inventory_servic.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestProductDTO(
        @NotBlank
        String name,
        @NotNull
        @Valid
        RequestNetContentDTO netContent,
        @NotNull
        Long categoryId,
        @NotNull
        BigDecimal price,
        String description,
        @NotBlank
        String brand,
        @NotNull
        @Valid
        RequestStockDTO stock

) {}
