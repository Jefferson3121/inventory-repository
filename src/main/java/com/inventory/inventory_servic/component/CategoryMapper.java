package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper{


    Category toCategory(RequestCategoryDTO categoryDTo);

    @Mapping(source = "category", target = "idCategoryParent")
    ResponseCategoryDTO toResponseCategoryDTO(Category category);

    @Named("toCategoryId")
    default int categoryToId(Category category){
        return category == null? null : category.getId();
    }
}
