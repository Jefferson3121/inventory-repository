package com.inventory.inventory_servic.controller;

import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductStockDTO;
import com.inventory.inventory_servic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody RequestProductDTO requestProductDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(requestProductDTO));
    }

    @PatchMapping
    public ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody RequestUpdateProductDTO updateProductDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(updateProductDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> getByIdProduct(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getByIdProduct(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseProductDTO>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/stock")
    public ResponseEntity<List<ResponseProductStockDTO>> getAllStock() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllStock());
    }


}
