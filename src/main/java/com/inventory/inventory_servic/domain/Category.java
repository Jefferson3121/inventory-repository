package com.inventory.inventory_servic.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Slf4j
@Entity
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Category {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @JoinColumn(name = "parent_category_id") @ManyToOne
    private Category parentCategory;
    private boolean active;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;





    public static Category createCategory(String name, String description, Category parentCategory){

        if(name == null ) throw  new IllegalArgumentException("El nombre de la categoria debe ser valido");
        return new Category(name, description, parentCategory);
    }


    public Category(){
        this.active = false;
        this.createdAt = LocalDateTime.now();
    }





    public Category(String name, String description, Category parentCategory){

        this();
        log.info("Metodo con parametros");


        this.name = name;
        this.description = description;
        this.parentCategory = parentCategory;



    }

    public void updateName(String name){

        if(name == null) throw new IllegalArgumentException("Debe incluir un valor valido para actualizar nombre");

        this.name = name;
    }

    public void updateDescription(String description){

        if (description == null) throw  new IllegalArgumentException("La nueva descripcion debe ser un valor valido");

        this.description = description;
    }


    public void updateParentCategory(Category parentCategory){

        if (parentCategory == null) throw new IllegalArgumentException("El valor para categoria debe ser valido");

        this.parentCategory = parentCategory;
    }

    public void activate(){
        this.active = true;
    }

    public void desactivate() {
        this.active = false;
    }



}
