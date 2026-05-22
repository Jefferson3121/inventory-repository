package com.inventory.inventory_servic.dto.response;

import com.inventory.inventory_servic.domain.UnitMeasurement;

import java.math.BigDecimal;

public record ResponseMeasurementDTO(
        BigDecimal value,
        UnitMeasurement unit) {
}
