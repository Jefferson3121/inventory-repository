package com.inventory.inventory_servic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sku;
    private String name;

    @Embedded
    private NetContent netContent;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;
    private BigDecimal price;
    private String description;
    private String brand;
    @Embedded
    private Stock stock;
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
//
//    protected Product(){}



    public static Product createProduct(String sku, String name, NetContent netContent, Category category, BigDecimal price, String description, String brand){


        if (sku == null ||
                name == null ||
                netContent == null ||
                category == null ||
                price == null ||
                brand == null)
            throw new IllegalArgumentException("No se permiten campos vacios \n Uno o mascampos estan vacios");


        if ( price.compareTo(BigDecimal.ZERO) < 0 )
            throw new IllegalArgumentException("El precio de un producto no pude ser menor a cero (0)");

        return new Product(sku, name, netContent, category, price, description, brand);

    }

    private Product(String sku, String name, NetContent netContent, Category category, BigDecimal price, String description, String brand){


        this.sku = sku;
        this.name = name;
        this.netContent = netContent;
        this.category = category;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.stock = new Stock(0, 0);

    }


    public void updateName(String newName){

        if(newName == null)throw new IllegalArgumentException("El nuevo nombre debe ser un valor valido");

        this.name = newName;
    }

    public void updateCategory(Category category){
        if(category == null) throw new IllegalArgumentException("Para actuslizar la categoria debe incluir un valor valido");

        this.category = category;
    }

    public void updatePrice(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) < 0)throw new IllegalArgumentException("El precio debe ser un valor valido mayor o igual a cero (0)");

        this.price = price;
    }



    public void updateDescription(String description){

        if ((description == null || description.isBlank())) throw new IllegalArgumentException("La nueva descripcion debe ser un valor valido");

        this.description = description;
    }




    public void increaseStock(long quantity){

        if (quantity <= 0) throw new IllegalArgumentException("Para incrementar stcok el valor debe ser mayor a cero (0)");

       Stock updateStock = new Stock(quantity + this.stock.getQuantity(), this.stock.getMinQuantity());

        this.stock = updateStock;
    }

    public void updateMinQuantityStock(long quantity){
        if (quantity < 0)throw new IllegalArgumentException("Para actualizar 'Stock minimo' debe incluir un valor valido(Mayor o igual a cero '0' ");

        Stock stockUpdate = new Stock(this.stock.getQuantity(), quantity);

        this.stock = stockUpdate;
    }


    public  void decreaseStock(long quantity){

        if (quantity > this.stock.getQuantity()) throw new IllegalArgumentException(String.format("No se puede reducir stock, cantidad a reducir (%d) es superior a catidad disponible", quantity));

        if (quantity <= 0) throw new IllegalArgumentException("Cantidad a reducir es invalidad, la cantidad debe ser mayor a cero ('0')");

        this.stock = new Stock(this.stock.getQuantity() - quantity, this.stock.getMinQuantity());
    }


    public void updateNetContent(NetContent netContent){

        if(netContent == null) throw new IllegalArgumentException("");


        this.netContent = new NetContent(netContent.getValue(), netContent.getUnit());
    }



}
