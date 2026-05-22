package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseStockDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
       )
public interface ProductMapper {

       Product toProduc(RequestProductDTO requestProductDTO);
       Product toUpdateProduct(RequestUpdateProductDTO requestUpdateProductDTO);
       ResponseProductDTO toResponseProduct(Product product);
       List<ResponseProductDTO> toProductDTOLIst(List<Product> products);
       List<ResponseStockDTO> toStockDTOList(List<Product> products);

}
