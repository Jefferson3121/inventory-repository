package com.inventory.inventory_servic.model;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private Unit unit;

    private BigDecimal netContent;

    @Setter
    private BigDecimal price;

    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Setter
    private ProductType type;

    @Setter
    private boolean available;  // Valor por defecto false, lo efine la cantidad que hay en el stock


    private String externalId;

    @Setter(AccessLevel.NONE)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    public Product(String name,String brand, Unit unit, BigDecimal netContent, BigDecimal price, Category category, ProductType type){
        this.name = name;
        this.brand = brand;
        this.unit = unit;
        this.netContent = netContent;
        this.price = price;
        this.category = category;
        this.type = type;
        this.externalId = UUID.randomUUID().toString();
        this.available = false;
    }
}
