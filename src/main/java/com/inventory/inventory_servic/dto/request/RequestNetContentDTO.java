package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.domain.UnitMeasurement;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestNetContentDTO(
        @NotNull(message = "El valor para la propiedad 'value' en NetConten no debe estar vacio")
        BigDecimal value,
        @NotNull(message = "El valor para la propiedad 'unit' en NetConten no debe estar vacio")
        UnitMeasurement unit
) {}
