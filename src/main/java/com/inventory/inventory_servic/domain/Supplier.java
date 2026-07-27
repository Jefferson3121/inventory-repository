package com.inventory.inventory_servic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Supplier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "contact_name")
    private String contactName;
    private String phone;
    private String email;

    @Embedded
    private Address address;

    private boolean active;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Supplier createSupplier(String name, String contactName, String phone, String email, Address address){

        List<String> camposVacios = new ArrayList<>();

        if (name == null || name.isBlank())   camposVacios.add("name");
        if (phone == null || phone.isBlank()) camposVacios.add("phone");
        if (email == null || email.isBlank()) camposVacios.add("email");
        if (address == null)                  camposVacios.add("address");

        if (!camposVacios.isEmpty())
            throw new IllegalArgumentException("Error al crear un nuevo proveedor: Los siguientes campos son obligatorios: " + String.join(", ", camposVacios));

        String normalizedName = normalize(name);

        return new Supplier(normalizedName, contactName, phone, email, address);
    }

    private Supplier(String name, String contactName, String phone, String email, Address address){
        this.name = name;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.active = true;
    }

    public static String normalize(String value) {
        return value.trim().toLowerCase().replaceAll("\\s+", " ");
    }

    public void updateName(String name){
        if (name == null || name.isBlank()) throw new IllegalArgumentException("El nombre debe ser un valor válido");
        this.name = normalize(name);
    }

    public void updatePhone(String phone){
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("El teléfono debe ser un valor válido");
        this.phone = phone;
    }

    public void updateEmail(String email){
        if (email == null || email.isBlank()) throw new IllegalArgumentException("El email debe ser un valor válido");
        this.email = email;
    }

    public void updateAddress(Address address){
        if (address == null) throw new IllegalArgumentException("La dirección debe ser un valor válido");
        this.address = new Address(address.getStreet(), address.getCity(), address.getState(), address.getZipCode(), address.getCountry());
    }

    public void activate(){
        this.active = true;
    }

    public void desactivate(){
        this.active = false;
    }
}
