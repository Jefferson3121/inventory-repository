package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.dto.RequestProductDTO;
import com.inventory.inventory_servic.dto.ResponseProductDTO;
import com.inventory.inventory_servic.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    Product dtoToProduct(RequestProductDTO requestProductDTO);
    ResponseProductDTO toResponseProducDto(Product product);
}
