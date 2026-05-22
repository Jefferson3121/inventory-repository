package com.inventory.inventory_servic.component;


import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateCategory;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CategoryMapper {

    Category toCategory(RequestCategoryDTO requestCategoryDTO);
    ResponseCategoryDTO toResponseCategoryDTO( Category category);
    Category toCategoryToUpdate(RequestUpdateCategory requestUpdateCategory);
    List<ResponseCategoryDTO> toListResponseCategoryDTO(List<Category> categoryList);
}
