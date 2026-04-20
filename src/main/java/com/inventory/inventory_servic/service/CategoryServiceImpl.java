package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.component.CategoryMapper;
import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateDescriptionDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.model.Category;
import com.inventory.inventory_servic.model.Product;
import com.inventory.inventory_servic.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;


    @Transactional
    @Override
    public ResponseCategoryDTO addCategory(RequestCategoryDTO categoryDTO){

        Category category = categoryMapper.toCategory(categoryDTO);
        category.setActive(true);

        if (categoryDTO.idCategoryParent() != null){ 
            Category categoryParent = categoryRepository.findById(categoryDTO.idCategoryParent())
                            .orElseThrow(() -> new RuntimeException("Objeto no econtrado")); // mejorar exepcion y mensaje
            category.setCategory(categoryParent);
        }

        Category categoryResponse = categoryRepository.save(category);

        return categoryMapper.toResponseCategoryDTO(categoryResponse);
    }

    @Override
    public void updateDescription(Long id, RequestUpdateDescriptionDTO newDescription){



    }

    @Transactional()
    @Override
    public void activateCategory(Integer id){

        validateId(id);

        Category category = categoryRepository.getById(id);
        category.setActive(!category.isActive());
    }

    @Override
    public void deleteCategory(Long id){

        Comparator<Product>

    }

    private void validateId(Integer id){
        if (id == null) throw new IllegalArgumentException("No se ingreso el id");//mejorar mensaje y hacer excepcion perzonalizada

        if (id < 1) throw new IllegalArgumentException("Id invalido"); // mejorar mensaje y validacion tamebine exepcion
    }
}
