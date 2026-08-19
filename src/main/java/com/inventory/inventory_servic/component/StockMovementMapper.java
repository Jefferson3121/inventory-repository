package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.domain.Batch;
import com.inventory.inventory_servic.domain.StockMovement;
import com.inventory.inventory_servic.dto.request.RequestStockMovementDTO;
import com.inventory.inventory_servic.dto.response.ResponseStockMovementDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StockMovementMapper {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "batch.product.name", target = "productName")
    ResponseStockMovementDTO toResponseStockMovementDTO(StockMovement stockMovement);

    default StockMovement toStockMovement(RequestStockMovementDTO dto, Batch batch){
        return StockMovement.createStockMovement(batch, dto.type(), dto.reason(), dto.quantity(), dto.notes());
    }

     List<ResponseStockMovementDTO> toListResponseStockMovementDTO(List<StockMovement> movements);
}
