package com.inventory.inventory_servic.service;


import com.inventory.inventory_servic.dto.request.RequestBatchDTO;
import com.inventory.inventory_servic.dto.response.ResponseBatchDTO;

import java.util.List;

public interface BatchService {
    ResponseBatchDTO createBatch(RequestBatchDTO requestBatchDTO);
    ResponseBatchDTO getByIdBatch(long id);
    List<ResponseBatchDTO> getAllBatches();
    List<ResponseBatchDTO> getBatchesByProduct(long productId);
    void deleteBatch(long id); // solo si no tiene movimientos
}