package com.inventory.inventory_servic.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "batch_code")
    private String batchCode;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    private long quantity;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public static Batch createBatch(Product product, String batchCode, LocalDate expirationDate, LocalDate manufacturingDate, long quantity){

        List<String> camposVacios = new ArrayList<>();

        if (product == null)           camposVacios.add("product");
        if (batchCode == null || batchCode.isBlank()) camposVacios.add("batchCode");
        if (expirationDate == null)    camposVacios.add("expirationDate");
        if (manufacturingDate == null) camposVacios.add("manufacturingDate");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al crear un nuevo lote: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        if (quantity <= 0)
            throw new IllegalArgumentException("La cantidad inicial del lote debe ser mayor a cero");

        if (expirationDate.isBefore(manufacturingDate))
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha de fabricación");

        return new Batch(product, batchCode, expirationDate, manufacturingDate, quantity);
    }

    private Batch(Product product, String batchCode, LocalDate expirationDate, LocalDate manufacturingDate, long quantity){
        this.product = product;
        this.batchCode = batchCode;
        this.expirationDate = expirationDate;
        this.manufacturingDate = manufacturingDate;
        this.quantity = quantity;
    }

    public void increaseQuantity(long amount){
        if (amount <= 0) throw new IllegalArgumentException("La cantidad a incrementar debe ser mayor a cero");
        this.quantity += amount;
    }

    public void decreaseQuantity(long amount){
        if (amount <= 0) throw new IllegalArgumentException("La cantidad a reducir debe ser mayor a cero");
        if (amount > this.quantity) throw new IllegalArgumentException("No se puede reducir más cantidad de la disponible en el lote");
        this.quantity -= amount;
    }

    public boolean isExpired(){
        return LocalDate.now().isAfter(this.expirationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Batch batch)) return false;
        return id != 0 && id == batch.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}