package com.inventory.inventory_servic.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
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

    private Category(String name, String description, Category parentCategory){
        this.name = name;
        this.description = description;
        this.parentCategory = parentCategory;
        this.active = false;
        this.createdAt = LocalDateTime.now();

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
