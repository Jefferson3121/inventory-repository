package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.component.CategoryMapper;
import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateCategoryDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.exception.DuplicateResourceException;
import com.inventory.inventory_servic.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseCategoryDTO createCategory(RequestCategoryDTO requestCategory) {

        if (categoryRepository.existsByName(requestCategory.name()))
            throw new DuplicateResourceException("Ya existe una categoria con el nombre: " + requestCategory.name());



        Category category = categoryMapper.toCategory(requestCategory);

        if (requestCategory.idParentCategory() != 0)
            category.updateParentCategory(categoryRepository.findById(requestCategory.idParentCategory())
                    .orElseThrow(() -> new IllegalArgumentException("Id de categoria padre no existe")));

        return categoryMapper.toResponseCategoryDTO(categoryRepository.save(category));
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

            category.updateParentCategory(parentCategory);
        }

        if ((requestUpdateCategoryDTO.active() != null)) {

            if (requestUpdateCategoryDTO.active() != category.isActive()) {

                if(requestUpdateCategoryDTO.active() == true){
                    category.desactivate();
                }else {
                    category.activate();
                }
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
        if (!existsById(idCategory)) throw new EntityNotFoundException(String.format("La categoria con id: %d no existe", idCategory));

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
