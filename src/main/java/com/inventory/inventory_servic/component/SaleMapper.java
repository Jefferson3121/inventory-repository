package com.inventory.inventory_servic.component;


import com.inventory.inventory_servic.domain.Batch;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.domain.Sale;
import com.inventory.inventory_servic.domain.SaleDetail;
import com.inventory.inventory_servic.dto.request.RequestSaleDTO;
import com.inventory.inventory_servic.dto.request.RequestSaleDetailDTO;
import com.inventory.inventory_servic.dto.response.ResponseSaleDTO;
import com.inventory.inventory_servic.dto.response.ResponseSaleDetailDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SaleMapper {

     default Sale toSaleCreate(RequestSaleDTO requestSaleDTO){
        return  Sale.createSale(requestSaleDTO.amountCollected(), requestSaleDTO.saleDate());
    }



    ResponseSaleDTO toResponseSaleDTO(Sale sale,BigDecimal expectedTotal,
                                      BigDecimal discrepancy, List<SaleDetail> details);

    @Mapping(source = "batch.batchCode", target = "batchCode")
    @Mapping(source = "product.name", target = "productName")
    ResponseSaleDetailDTO toResponseSaleDetailDTO(SaleDetail detail);

    default SaleDetail toSaleDetail(RequestSaleDetailDTO requestSaleDetailDTO, Sale sale, Product product, Batch batchCode){
        return SaleDetail.createSaleDetail(sale, product, batchCode, requestSaleDetailDTO.quantity(), requestSaleDetailDTO.unitPrice());
    }



}