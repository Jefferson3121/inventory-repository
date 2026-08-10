package com.inventory.inventory_servic.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    @Enumerated(EnumType.STRING)
    private MovementReason reason;

    private long quantity;

    private String notes;

    @Column(name = "movement_date")
    @CreatedDate
    private LocalDateTime movementDate;

    public static StockMovement createStockMovement(Batch batch, MovementType type, MovementReason reason, long quantity, String notes){

        List<String> camposVacios = new ArrayList<>();

        if (batch == null)    camposVacios.add("batch");
        if (type == null)     camposVacios.add("type");
        if (reason == null)   camposVacios.add("reason");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al registrar movimiento de stock: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        if (quantity <= 0)
            throw new IllegalArgumentException("La cantidad del movimiento debe ser mayor a cero");

        if (type == MovementType.IN)
            batch.increaseQuantity(quantity);
        else
            batch.decreaseQuantity(quantity);

        return new StockMovement(batch, type, reason, quantity, notes);
    }

    private StockMovement(Batch batch, MovementType type, MovementReason reason, long quantity, String notes){
        this.batch = batch;
        this.type = type;
        this.reason = reason;
        this.quantity = quantity;
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockMovement that)) return false;
        return id != 0 && id == that.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}