package com.inventory.inventory_servic.service;


import com.inventory.inventory_servic.dto.request.RequestSaleDTO;
import com.inventory.inventory_servic.dto.response.ResponseSaleDTO;

import java.util.List;

public interface SaleService {
    ResponseSaleDTO createSale(RequestSaleDTO requestSaleDTO);
    ResponseSaleDTO getByIdSale(long id);
    List<ResponseSaleDTO> getAllSales();
}
