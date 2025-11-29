package com.inventory.inventory_servic.controller;

import com.inventory.inventory_servic.dto.request.RequestChangeCategory;
import com.inventory.inventory_servic.dto.request.RequestChangeTypeDTO;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdataPrice;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

   private final ProductService productService;

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

    @PatchMapping("/{id}/type")
    public ResponseEntity<?> changeType(@Valid @PathVariable long id, @RequestBody RequestChangeTypeDTO changeTypeDTO){

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@Valid @PathVariable long id){

    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){

    }
}
