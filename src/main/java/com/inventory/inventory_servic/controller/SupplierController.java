package com.inventory.inventory_servic.controller;

import com.inventory.inventory_servic.dto.request.RequestSupplierDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateSupplierDTO;
import com.inventory.inventory_servic.dto.response.ResponseSupplierDTO;
import com.inventory.inventory_servic.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ResponseSupplierDTO> create(@Valid @RequestBody RequestSupplierDTO requestSupplierDTO) {
        ResponseSupplierDTO response = supplierService.createSupplier(requestSupplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSupplierDTO> update(@PathVariable long id,
                                                      @Valid @RequestBody RequestUpdateSupplierDTO requestUpdateSupplierDTO) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, requestUpdateSupplierDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSupplierDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(supplierService.getByIdSupplier(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseSupplierDTO>> getAll() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }



    @PostMapping("/{supplierId}/products/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable long supplierId, @PathVariable long productId) {
        supplierService.addProductToSupplier(supplierId, productId);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{supplierId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable long supplierId, @PathVariable long productId) {
        supplierService.removeProductFromSupplier(supplierId, productId);
        return ResponseEntity.noContent().build();
    }

}
