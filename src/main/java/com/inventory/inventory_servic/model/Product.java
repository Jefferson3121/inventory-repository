package com.inventory.inventory_servic.model;

import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private BigDecimal netContent;// CAmbiar nombre en la BD 🧿

    private BigDecimal price;

    //hacer lo de one to many y viceversa
    private Category category;

    private  ProductType  type;

    private boolean available;  // Valor por defecto false, lo efine la cantidad que hay en el stock
    //Agregar que jpa no haga las actualizacines automaticamente


    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    public Product(String name,String brand, Unit unit, BigDecimal netContent, BigDecimal price, Category category, ProductType type){
        this.name = name;
        this.brand = brand;
        this.unit = unit;
        this.netContent = netContent;
        this.price = price;
        this.category = category;
        this.available = false;
    }




    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;

        if (!(obj instanceof  Product)){
            return false;
        }

        Product other = (Product) obj;

        return  Objects.equals(this.name, other.name)
                && Objects.equals(this.brand, other.brand)
                && Objects.equals(this.unit, other.unit);
    }

    @Override
    public int  hashCode(){
        return Objects.hash(this.name,this.brand,this.unit);
    }

}
