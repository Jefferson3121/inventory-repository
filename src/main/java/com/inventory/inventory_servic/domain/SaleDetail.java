package com.inventory.inventory_servic.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private long quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    public static SaleDetail createSaleDetail(Sale sale, Product product, Batch batch, long quantity, BigDecimal unitPrice){

        List<String> camposVacios = new ArrayList<>();

        if (sale == null)      camposVacios.add("sale");
        if (product == null)   camposVacios.add("product");
        if (batch == null)     camposVacios.add("batch");
        if (unitPrice == null) camposVacios.add("unitPrice");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al crear detalle de venta: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        if (quantity <= 0)
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");

        if (unitPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");

        return new SaleDetail(sale, product, batch, quantity, unitPrice);
    }

    private SaleDetail(Sale sale, Product product, Batch batch, long quantity, BigDecimal unitPrice){
        this.sale = sale;
        this.product = product;
        this.batch = batch;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleDetail that)) return false;
        return id != 0 && id == that.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
