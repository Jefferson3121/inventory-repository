package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.component.CategoryMapper;
import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateCategoryDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper    categoryMapper;



    @Transactional
    @Override
    public ResponseCategoryDTO createCategory( RequestCategoryDTO requestCategory) {

        log.info(String.format("antes del save %s", requestCategory.name()));

            Category category = categoryRepository.save(categoryMapper.toCategory(requestCategory));

            log.info("Despues del save \n  %s  \n  %s ", requestCategory.name(), requestCategory.description());

        return categoryMapper.toResponseCategoryDTO(category);
    }



    @Transactional
    @Override
    public ResponseCategoryDTO updateCategory(long id, RequestUpdateCategoryDTO requestUpdateCategoryDTO) {


        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("La categoria que intenta actualizar no existe"));

        if (requestUpdateCategoryDTO.name() != null) category.updateName(requestUpdateCategoryDTO.name());

        if(requestUpdateCategoryDTO.description() != null) category.updateDescription(requestUpdateCategoryDTO.description());

        if(requestUpdateCategoryDTO.parentCategoryId() > 0 && categoryRepository.existsById(requestUpdateCategoryDTO.parentCategoryId())){

            Category parentCategory = categoryRepository.findById(requestUpdateCategoryDTO.parentCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("ParentCategory de Id: %d no existe", requestUpdateCategoryDTO.parentCategoryId())));
        }


        if (requestUpdateCategoryDTO.active() != category.isActive()){

            if (category.isActive() == true) {
                category.desactivate();
            } else {
                category.activate();
            }
        }


        return categoryMapper.toResponseCategoryDTO(category);
    }



    @Override
    public ResponseCategoryDTO getCategory(long idCategory) {
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Categoria con id = %d no existe", idCategory)));
        return categoryMapper.toResponseCategoryDTO(category);
    }



    @Override
    public void deleteCategory(long idCategory) {
        categoryRepository.deleteById(idCategory);
    }




    @Override
    public List<ResponseCategoryDTO> getAllCategorys() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.toListResponseCategoryDTO(categoryList);
    }



    @Override
    public boolean existsById(long id){
        return categoryRepository.existsById(id);
    }
}
