package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.domain.NetContent;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.domain.Stock;
import com.inventory.inventory_servic.dto.request.RequestNetContentDTO;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestStockDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseNetContentDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductStockDTO;
import com.inventory.inventory_servic.dto.response.ResponseStockDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;



@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
       )
public interface ProductMapper {

//       Product toProduct(RequestProductDTO requestProductDTO);
       Product toUpdateProduct(RequestUpdateProductDTO requestUpdateProductDTO);
       ResponseProductDTO toResponseProduct(Product product);
       ResponseProductStockDTO toProductStockResponseDTO(Product product);
       ResponseNetContentDTO toResponseNetContentDTO(NetContent netContent);
       ResponseStockDTO toResponseStockDTO(Stock stock);
       Stock toStock(RequestStockDTO requestStockDTO);
       NetContent toNetContent(RequestNetContentDTO requestNetContentDTO);



       default NetContent mapNetContent(RequestNetContentDTO netContentDTO) {
              if (netContentDTO == null) {
                     return null;
              }
              return new NetContent(
                      netContentDTO.value(),
                      netContentDTO.unit()
              );
       }


       default Product toProduct(RequestProductDTO requestProductDTO){

              return  Product.createProduct(
                      null,
                      requestProductDTO.name(),
                      ,
                      null,
                      requestProductDTO.price(),
                      requestProductDTO.description(),
                      requestProductDTO.brand()
              )

       }

}
