package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.domain.UnitMeasurement;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestNetContentDTO(
        @NotNull
        BigDecimal value,
        @NotNull
        UnitMeasurement unit
) {}
