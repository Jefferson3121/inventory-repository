package com.inventory.inventory_servic.controller;

import com.inventory.inventory_servic.component.ProductMapper;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseStockDTO;
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
    private final ProductMapper productMapper;


    @PostMapping()
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody RequestProductDTO requestProductDTO){

       Product product = productService.createProduct(productMapper.toProduc(requestProductDTO));

       return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toResponseProduct(product));

    }


    @PatchMapping
    public ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody RequestUpdateProductDTO updateProductDTO){

        Product product = productService.updateProduct(productMapper.toUpdateProduct(updateProductDTO));

        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toResponseProduct(product));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteProduct(@PathVariable long idProduct){
        productService.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




    @GetMapping("/id")
    public ResponseEntity<ResponseProductDTO> getByIdProduct(@PathVariable long idProduct){

        Product product = productService.getByIdProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toResponseProduct(product));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseProductDTO>> getAllProducts(){

        List<Product> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toProductDTOLIst(products));
    }


    @GetMapping("/stock")
    public ResponseEntity<List<ResponseStockDTO>> getAllStock(){

        List<Product> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toStockDTOList(products));
    }


}
