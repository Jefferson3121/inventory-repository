package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.domain.NetContent;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.domain.Stock;
import com.inventory.inventory_servic.dto.request.RequestNetContentDTO;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestStockDTO;
import com.inventory.inventory_servic.dto.response.ResponseNetContentDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductStockDTO;
import com.inventory.inventory_servic.dto.response.ResponseStockDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
       )
public interface ProductMapper {

//       Product toUpdateProduct(RequestUpdateProductDTO requestUpdateProductDTO);
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


       default Product toProduct(RequestProductDTO requestProductDTO, Category category){

              return  Product.createProduct(
                      requestProductDTO.name(),
                      toNetContent(requestProductDTO.netContent()),
                      category,
                      requestProductDTO.price(),
                      requestProductDTO.description(),
                      requestProductDTO.brand(),
                      toStock(requestProductDTO.stock())
              );

       }




       default ResponseProductDTO toResponseProduct(Product product){

              return new ResponseProductDTO(
                      product.getId(),
                      product.getName(),
                      toResponseNetContentDTO(product.getNetContent()),
                      product.getCategory().getName(),
                      product.getPrice(),
                      product.getDescription(),
                      product.getBrand(),
                      toResponseStockDTO(product.getStock()),
                      product.getCreatedAt(),
                      product.getUpdatedAt()
              );
       }

}
