package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.domain.Batch;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.dto.request.RequestBatchDTO;
import com.inventory.inventory_servic.dto.response.ResponseBatchDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BatchMapper {

    @Mapping(source = "product.name", target = "productName")
    @Mapping(target = "expired", expression = "java(batch.isExpired())")
    ResponseBatchDTO toResponseBatchDTO(Batch batch);

    default Batch toBatch(RequestBatchDTO dto, Product product){
        return Batch.createBatch(product, dto.expirationDate(), dto.manufacturingDate(), dto.quantity());
    }

    List<ResponseBatchDTO> toListResponseBatchDTO(List<Batch> batches);
}
