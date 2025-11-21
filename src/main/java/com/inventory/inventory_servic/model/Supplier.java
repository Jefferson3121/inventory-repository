package com.inventory.inventory_servic.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Supplier {

    //id SERIAL PRIMARY KEY,
    //    name VARCHAR(150) NOT NULL,
    //    contact_name VARCHAR(100), --> otra tabla
    //    phone VARCHAR(20), --> otra tabla
    //    email VARCHAR(100), --> otra tabla
    //    address TEXT, --> otra tabla
    //    created_at TIMESTAMP DEFAULT NOW(),
    //    updated_at TIMESTAMP DEFAULT NOW()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt; // pensar bine utilidad
    @Column(name = "updated_at")
    private LocalDateTime updateAt; //pensar bien la utilidad
}
