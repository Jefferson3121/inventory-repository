package com.inventory.inventory_servic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;


import java.math.BigDecimal;

@Getter
@Embeddable
public class NetContent {

    @Column(name = "net_content_value")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private UnitMeasurement unit;

    protected NetContent(){}


    public NetContent(BigDecimal value, UnitMeasurement unit){
        this.value = value;
        this.unit = unit;
    }
}