package com.inventory.inventory_servic.repository;

import com.inventory.inventory_servic.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByNameAndPhoneAndEmail(String name, String phone, String email);

    @Modifying
    @Query(value = "INSERT INTO supplier_product (supplier_id, product_id) VALUES (:supplierId, :productId)", nativeQuery = true)
    void linkProduct(@Param("supplierId") long supplierId, @Param("productId") long productId);

    @Modifying
    @Query(value = "DELETE FROM supplier_product WHERE supplier_id = :supplierId AND product_id = :productId", nativeQuery = true)
    void unlinkProduct(@Param("supplierId") long supplierId, @Param("productId") long productId);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM supplier_product WHERE supplier_id = :supplierId AND product_id = :productId)", nativeQuery = true)
    boolean existsLink(@Param("supplierId") long supplierId, @Param("productId") long productId);
}
