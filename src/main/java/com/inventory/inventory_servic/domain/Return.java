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
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sale_detail_id")
    private SaleDetail saleDetail;

    private long quantity;

    @Enumerated(EnumType.STRING)
    private ReturnReason reason;

    @Column(name = "return_date")
    @CreatedDate
    private LocalDateTime returnDate;

    private String notes;

    public static Return createReturn(SaleDetail saleDetail, long quantity, ReturnReason reason, String notes){

        List<String> camposVacios = new ArrayList<>();

        if (saleDetail == null) camposVacios.add("saleDetail");
        if (reason == null)     camposVacios.add("reason");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al registrar devolución: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        if (quantity <= 0)
            throw new IllegalArgumentException("La cantidad a devolver debe ser mayor a cero");

        return new Return(saleDetail, quantity, reason, notes);
    }

    private Return(SaleDetail saleDetail, long quantity, ReturnReason reason, String notes){
        this.saleDetail = saleDetail;
        this.quantity = quantity;
        this.reason = reason;
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Return aReturn)) return false;
        return id != 0 && id == aReturn.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
