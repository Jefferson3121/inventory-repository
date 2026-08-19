package com.inventory.inventory_servic.component;


import com.inventory.inventory_servic.domain.Return;
import com.inventory.inventory_servic.domain.SaleDetail;
import com.inventory.inventory_servic.dto.request.RequestReturnDTO;
import com.inventory.inventory_servic.dto.request.ResponseReturnDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReturnMapper {

    @Mapping(source = "saleDetail.id", target = "saleDetailId")
    @Mapping(source = "saleDetail.product.name", target = "productName")
    ResponseReturnDTO toResponseReturnDTO(Return aReturn);

    default Return toReturn(RequestReturnDTO dto, SaleDetail saleDetail){
        return Return.createReturn(saleDetail, dto.quantity(), dto.reason(), dto.notes());
    }
}