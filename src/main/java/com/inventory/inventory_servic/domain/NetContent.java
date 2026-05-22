package com.inventory.inventory_servic.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Embeddable
public class NetContent {

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private UnitMeasurement unit;
}