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

    private BigDecimal quantity;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    protected Batch(){}

    public static Batch createBatch(Product product, LocalDate expirationDate, LocalDate manufacturingDate, BigDecimal quantity){

        List<String> camposVacios = new ArrayList<>();

        if (product == null)           camposVacios.add("product");
        if (expirationDate == null)    camposVacios.add("expirationDate");
        if (manufacturingDate == null) camposVacios.add("manufacturingDate");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al crear un nuevo lote: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        if (quantity.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("La cantidad inicial del lote debe ser mayor a cero");

        if (expirationDate.isBefore(manufacturingDate))
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha de fabricación");

        return new Batch(product, expirationDate, manufacturingDate, quantity);
    }

    private Batch(Product product, LocalDate expirationDate, LocalDate manufacturingDate, BigDecimal quantity){
        this.product = product;
        this.batchCode = "LOTE-%06d".formatted(this.id);
        this.expirationDate = expirationDate;
        this.manufacturingDate = manufacturingDate;
        this.quantity = quantity;
    }




    public void increaseQuantity(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("La cantidad a incrementar debe ser mayor a cero");
        this.quantity.add(amount);
    }

    public void decreaseQuantity(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("La cantidad a reducir debe ser mayor a cero");
        if (amount.compareTo(this.quantity) < 0) throw new IllegalArgumentException("No se puede reducir más cantidad de la disponible en el lote");
        this.quantity.subtract(amount);
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