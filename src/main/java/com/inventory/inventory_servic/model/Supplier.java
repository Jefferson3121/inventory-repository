package com.inventory.inventory_servic.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    private List<Product> products;

    @Setter(AccessLevel.NONE)
    @NonNull
    @Column(name = "created_at")
    private LocalDateTime createdAt; // pensar bine utilidad  y el valor de la inicializacion

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt; //pensar bien la utilidad
}
