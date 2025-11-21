package com.inventory.inventory_servic.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Category {
    //id SERIAL PRIMARY KEY,
    //    name VARCHAR(100) NOT NULL,
    //    description TEXT,
    //    parent_id INT REFERENCES category(id),  -- categoría padre (opcional)
    //    created_at TIMESTAMP DEFAULT NOW(),
    //    updated_at TIMESTAMP DEFAULT NOW()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String name;
    private String description;
    //Lo de one to many para lo de las rfrencias por clave foranea de jpa
    private Category category;
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Setter(AccessLevel.NONE) @Column(name = "updated_at")
    private LocalDateTime updateAt;


    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Category(String name){
        this(name, null);
    }


}
