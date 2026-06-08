package com.inventory.inventory_servic.component;


import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateCategoryDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE

)
public interface CategoryMapper {

//    @Mapping(target = "categoryId", ignore = true)
    Category toCategory(RequestCategoryDTO requestCategoryDTO);


    ResponseCategoryDTO toResponseCategoryDTO( Category category);
    Category toCategoryToUpdate(RequestUpdateCategoryDTO requestUpdateCategoryDTO);
    List<ResponseCategoryDTO> toListResponseCategoryDTO(List<Category> categoryList);
}
