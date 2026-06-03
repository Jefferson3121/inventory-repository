package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.component.CategoryMapper;
import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateCategoryDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;



    @Transactional
    @Override
    public ResponseCategoryDTO createCategory(RequestCategoryDTO requestCategory) {


            Category category = categoryRepository.save(categoryMapper.toCategory(requestCategory));

        return categoryMapper.toResponseCategoryDTO(category);
    }



    @Override
    public ResponseCategoryDTO updateCategory(long id, RequestUpdateCategoryDTO requestUpdateCategoryDTO) {

        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("La categoria que intenta actualizar no existe");
        }
        Category category = categoryRepository.save(categoryMapper.toCategoryToUpdate(requestUpdateCategoryDTO));
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
