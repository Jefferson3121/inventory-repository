package com.inventory.inventory_servic.controller;


import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateCategoryDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<ResponseCategoryDTO> createCategory(@RequestBody @Valid RequestCategoryDTO requestCategoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(requestCategoryDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseCategoryDTO> updateCategory(@PathVariable long id,@RequestBody @Valid RequestUpdateCategoryDTO requestUpdateCategoryDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, requestUpdateCategoryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategoryDTO> getCategory(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseCategoryDTO>> getAllCategorys() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategorys());
    }











}
