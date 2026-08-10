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
public class PurchaseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private BigDecimal quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    public static PurchaseDetail createPurchaseDetail(PurchaseOrder purchaseOrder, Product product,Batch batch, BigDecimal quantity, BigDecimal unitPrice){

        List<String> camposVacios = new ArrayList<>();

        if (purchaseOrder == null) camposVacios.add("purchaseOrder");
        if (product == null)       camposVacios.add("product");
        if (unitPrice == null)     camposVacios.add("unitPrice");
        if (batch == null) camposVacios.add("bach");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al crear detalle de compra: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        if (quantity.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");

        if (unitPrice.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");



        if (batch.getProduct() != product)  throw new IllegalArgumentException("El lote no pertenece a ese producto");

        return new PurchaseDetail(purchaseOrder, product,batch, quantity, unitPrice);
    }

    private PurchaseDetail(PurchaseOrder purchaseOrder, Product product,Batch batch, BigDecimal quantity, BigDecimal unitPrice){
        this.purchaseOrder = purchaseOrder;
        this.product = product;
        this.batch = batch;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

//    public void assignBatch(Batch batch){
//        if (batch == null) throw new IllegalArgumentException("El lote debe ser un valor válido");
//        this.batch = batch;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseDetail that)) return false;
        return id != 0 && id == that.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
