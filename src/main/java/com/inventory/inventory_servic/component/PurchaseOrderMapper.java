package com.inventory.inventory_servic.component;

import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.domain.PurchaseDetail;
import com.inventory.inventory_servic.domain.PurchaseOrder;
import com.inventory.inventory_servic.dto.request.RequestPurchaseDetailDTO;
import com.inventory.inventory_servic.dto.response.ResponsePurchaseDetailDTO;
import com.inventory.inventory_servic.dto.response.ResponsePurchaseOrderDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PurchaseOrderMapper {

    @Mapping(source = "batch.batchCode", target = "batchCode")
    @Mapping(source = "product.name", target = "productName")
    ResponsePurchaseDetailDTO toResponsePurchaseDetailDTO(PurchaseDetail detail);



    default PurchaseDetail toPurchaseDetail(RequestPurchaseDetailDTO requestPurchaseDetailDTO, PurchaseOrder purchaseOrder, Product product){
        return PurchaseDetail.createPurchaseDetail(purchaseOrder, product, requestPurchaseDetailDTO.quantity(), requestPurchaseDetailDTO.unitPrice());
    }



    ResponsePurchaseOrderDTO toResponsePurchaseOrderDTO(PurchaseOrder order, List<PurchaseDetail> details);
}
