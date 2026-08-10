package com.inventory.inventory_servic.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus status;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public static PurchaseOrder createPurchaseOrder(Supplier supplier, LocalDate orderDate){

            List<String> camposVacios = new ArrayList<>();

        if (supplier == null)  camposVacios.add("supplier");
        if (orderDate == null) camposVacios.add("orderDate");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al crear orden de compra: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        return new PurchaseOrder(supplier, orderDate);
    }

    private PurchaseOrder(Supplier supplier, LocalDate orderDate){
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.status = PurchaseOrderStatus.PENDING;
    }

    public void markAsReceived(){
        if (this.status != PurchaseOrderStatus.PENDING)
            throw new IllegalArgumentException("Solo se puede recibir una orden que está pendiente");
        this.status = PurchaseOrderStatus.RECEIVED;
    }

    public void markSsCancelled(){
        if (this.status != PurchaseOrderStatus.PENDING)
            throw new IllegalArgumentException("Solo se puede cancelar una orden que está pendiente");
        this.status = PurchaseOrderStatus.CANCELLED;
    }



    public void editSupplier(Supplier supplier){

        if (status != PurchaseOrderStatus.PENDING) throw new IllegalStateException("No puede editar el proveedor de una orden de compra en estado recibido o cancelado");

        this.supplier = supplier;
    }




    public PurchaseDetail addPurchaseDetail(Product product, Batch batch, BigDecimal quantity, BigDecimal unitPrice){

        return PurchaseDetail.createPurchaseDetail(this,product, batch, quantity, unitPrice);
    }


    public void deletePurchasedetail(long idPurcharseDetail){
        if (status != PurchaseOrderStatus.PENDING) throw new IllegalStateException("No puede eliminar un detalle de una compra en estado aprobado o cancelado");
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrder that)) return false;
        return id != 0 && id == that.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
