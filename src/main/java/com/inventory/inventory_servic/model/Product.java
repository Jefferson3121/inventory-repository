package com.inventory.inventory_servic.model;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private String name;

    @Setter(AccessLevel.NONE)
    private String brand;

    @Setter(AccessLevel.NONE)
    private Unit unit;

    @Setter(AccessLevel.NONE)
    private BigDecimal netContent;


    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private ProductType type;

    private boolean available;  // Valor por defecto false, lo efine la cantidad que hay en el stock

    //LO de mani to many
    private List<Supplier> suppliers;

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
        this.suppliers = new ArrayList<>();
        this.available = false;
    }
}
