package com.inventory.inventory_servic.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
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
    private Integer id;
    private String name;

    @Setter
    private String description;

    private String slug; //Agregar en bd

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category category;

    @Setter
    private boolean active; //Agregar en BD

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE) @Column(name = "updated_at")
    private LocalDateTime updateAt;


    public Category(String name, String description){
        this.name = name;
        this.description = description;
        this.active = false;
    }
}
