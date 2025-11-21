package com.inventory.inventory_servic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "product_supplier")
public class ProviderSupplier {

    //product_id INT REFERENCES product(id) ON DELETE CASCADE,
    //    supplier_id INT REFERENCES supplier(id) ON DELETE CASCADE,
    //    supply_price NUMERIC(10,2), -- el precio al que el proveedor lo vende
    //    PRIMARY KEY (product_id, supplier_id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // ojito aqui  PRIMARY KEY (product_id, supplier_id)
    //Hacer lo de one to many
    private  Supplier supplier;
    private BigDecimal supplyPrice; //Precio al que el proveedor lo vende (no me convence este campo)

    public ProviderSupplier(Supplier supplier, BigDecimal supplyPrice) {
        this.supplier = supplier;
        this.supplyPrice = supplyPrice;
    }
}
