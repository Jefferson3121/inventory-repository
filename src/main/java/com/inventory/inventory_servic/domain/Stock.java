package com.inventory.inventory_servic.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Stock {
    private long quantity;
    private long minQuantity;  // para alertas de reabastecimiento

    protected Stock(){}

    public Stock(long quantity, long minQuantity){
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }





    public boolean isLow() {
        return quantity <= minQuantity;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }
}
