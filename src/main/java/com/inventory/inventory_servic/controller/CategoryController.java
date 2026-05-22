package com.inventory.inventory_servic.controller;


import com.inventory.inventory_servic.component.CategoryMapper;
import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateCategory;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @PostMapping
    public ResponseEntity<ResponseCategoryDTO> createCategory(RequestCategoryDTO requestCategoryDTO){

        Category category = categoryService.createCategory(categoryMapper.toCategory(requestCategoryDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toResponseCategoryDTO(category));
    }


    @PatchMapping
    public ResponseEntity<ResponseCategoryDTO> updateCategory(int id, RequestUpdateCategory requestUpdateCategory){

        Category category = categoryService.updateCategory(id, categoryMapper.toCategoryToUpdate(requestUpdateCategory));

        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.toResponseCategoryDTO(category));
    }



    @GetMapping("/id")
    public ResponseEntity<ResponseCategoryDTO> getCategory(@PathVariable int idCategory){

        Category category = categoryService.getCategory(idCategory);

        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.toResponseCategoryDTO(category));
    }


    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteCategory(int idCategory){

        categoryService.deleteCategory(idCategory);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<ResponseCategoryDTO>> getAllCategorys(){

        List<Category> categoryList = categoryService.getAllCategorys();

        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.toListResponseCategoryDTO(categoryList));
    }

}
