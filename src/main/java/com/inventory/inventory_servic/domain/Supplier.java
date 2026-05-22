package com.inventory.inventory_servic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate supplierSince;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
