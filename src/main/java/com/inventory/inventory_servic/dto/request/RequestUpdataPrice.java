package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestUpdataPrice(@NotNull BigDecimal price) {}
