package com.inventory.inventory_servic.service;


import com.inventory.inventory_servic.dto.request.RequestPurchaseOrderDTO;
import com.inventory.inventory_servic.dto.response.ResponsePurchaseOrderDTO;

import java.util.List;

public interface PurchaseOrderService {
    ResponsePurchaseOrderDTO createPurchaseOrder(RequestPurchaseOrderDTO requestPurchaseOrderDTO);
    ResponsePurchaseOrderDTO receivePurchaseOrder(long id);
    ResponsePurchaseOrderDTO cancelPurchaseOrder(long id);
    ResponsePurchaseOrderDTO getByIdPurchaseOrder(long id);
    List<ResponsePurchaseOrderDTO> getAllPurchaseOrders();
}