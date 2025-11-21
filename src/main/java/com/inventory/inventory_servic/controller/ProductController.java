package com.inventory.inventory_servic.controller;

import com.inventory.inventory_servic.dto.RequestChangeCategory;
import com.inventory.inventory_servic.dto.RequestProductDTO;
import com.inventory.inventory_servic.dto.RequestUpdataPrice;
import com.inventory.inventory_servic.dto.ResponseProductDTO;
import com.inventory.inventory_servic.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<ResponseProductDTO> addProduct(@RequestBody @Valid RequestProductDTO requestProductDTO){

        ResponseProductDTO created = productService.create(requestProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<?> updatePrice(@Valid @PathVariable Long id,@RequestBody RequestUpdataPrice price){

        productService.updatePrice(id, price);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}/category")
    public ResponseEntity<?> changeCategory(@Valid @PathVariable Long id, @RequestBody RequestChangeCategory requestChangeCategory){

        productService.changeCategory(id, requestChangeCategory);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
