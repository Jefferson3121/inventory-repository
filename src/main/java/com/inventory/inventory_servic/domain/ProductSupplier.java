package com.inventory.inventory_servic.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductSupplier {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
    private Product product;

//    @ManyToOne
//    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private BigDecimal costPrice;
    private boolean isPrimary;
}
