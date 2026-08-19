package com.inventory.inventory_servic.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount_collected")
    private BigDecimal amountCollected;

    @Column(name = "sale_date")
    @CreatedDate
    private LocalDateTime saleDate;





    protected Sale(){}


    private Sale(BigDecimal amountCollected, LocalDateTime saleDate){
        this.amountCollected = amountCollected;
        this.saleDate = saleDate;
    }



    public static Sale createSale(BigDecimal amountCollected, LocalDateTime saleDate){

        List<String> emptyfields = new ArrayList<>();

        if (amountCollected == null) emptyfields.add("amountCollected");
        if (saleDate == null) emptyfields.add("saleDate");


        if (!emptyfields.isEmpty()) throw new IllegalArgumentException("Error al crear un nuevo lote: Los siguientes campos son obligatorios: " + String.join(", ", emptyfields));


        return new Sale(amountCollected, saleDate);
    }




    public SaleDetail addSaleDatil(Product product, Batch batch, BigDecimal quantity, BigDecimal unitPrice){

         return SaleDetail.createSaleDetail(this, product,batch, quantity, unitPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale sale)) return false;
        return id != 0 && id == sale.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
