package com.inventory.inventory_servic.controller;

import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateDescriptionDTO;
import com.inventory.inventory_servic.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorys")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@Valid @RequestBody RequestCategoryDTO categoryDTO){

    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<?> updateDescription(@Valid @PathVariable long id, RequestUpdateDescriptionDTO newDescription){

    }

    @PatchMapping("/{id}/activate")
    public  ResponseEntity<?> activateCategory(@Valid @PathVariable long id){

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@Valid @PathVariable long id){

    }


}
