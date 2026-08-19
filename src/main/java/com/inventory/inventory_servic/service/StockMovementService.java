package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestStockMovementDTO;
import com.inventory.inventory_servic.dto.response.ResponseStockMovementDTO;

import java.util.List;

public interface StockMovementService {
    ResponseStockMovementDTO createStockMovement(RequestStockMovementDTO requestStockMovementDTO);
    List<ResponseStockMovementDTO> getMovementsByBatch(long batchId);
    List<ResponseStockMovementDTO> getAllMovements();
}