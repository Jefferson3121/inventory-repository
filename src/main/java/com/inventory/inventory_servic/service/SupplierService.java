package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestSupplierDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateSupplierDTO;
import com.inventory.inventory_servic.dto.response.ResponseSupplierDTO;

import java.util.List;

public interface SupplierService {
    ResponseSupplierDTO createSupplier(RequestSupplierDTO requestSupplierDTO);
    ResponseSupplierDTO updateSupplier(long id, RequestUpdateSupplierDTO requestUpdateSupplierDTO);
    void deleteSupplier(long id);
    ResponseSupplierDTO getByIdSupplier(long id);
    List<ResponseSupplierDTO> getAllSuppliers();
    void addProductToSupplier(long supplierId, long productId);
    void removeProductFromSupplier(long supplierId, long productId);
}
