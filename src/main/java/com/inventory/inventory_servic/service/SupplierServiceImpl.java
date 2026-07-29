package com.inventory.inventory_servic.service;


import com.inventory.inventory_servic.component.SupplierMapper;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.domain.Supplier;
import com.inventory.inventory_servic.dto.request.RequestSupplierDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateSupplierDTO;
import com.inventory.inventory_servic.dto.response.ResponseSupplierDTO;
import com.inventory.inventory_servic.repository.ProductRepository;
import com.inventory.inventory_servic.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public ResponseSupplierDTO createSupplier(RequestSupplierDTO requestSupplierDTO) {

        String normalizedName = Supplier.normalize(requestSupplierDTO.name());

        if (supplierRepository.existsByNameAndPhoneAndEmail(
                normalizedName, requestSupplierDTO.phone(), requestSupplierDTO.email())) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese nombre, teléfono y email");
        }

        Supplier supplier = supplierMapper.toSupplier(requestSupplierDTO);

        try {
            return supplierMapper.toResponseSupplierDTO(supplierRepository.save(supplier));
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Ya existe un proveedor con esas características");
        }
    }

    @Transactional
    @Override
    public ResponseSupplierDTO updateSupplier(long id, RequestUpdateSupplierDTO requestUpdateSupplierDTO) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El proveedor que intenta actualizar no existe"));

        if (requestUpdateSupplierDTO.name() != null)
            supplier.updateName(requestUpdateSupplierDTO.name());

        if (requestUpdateSupplierDTO.phone() != null)
            supplier.updatePhone(requestUpdateSupplierDTO.phone());

        if (requestUpdateSupplierDTO.email() != null)
            supplier.updateEmail(requestUpdateSupplierDTO.email());

        if (requestUpdateSupplierDTO.address() != null)
            supplier.updateAddress(supplierMapper.mapAddress(requestUpdateSupplierDTO.address()));

        if (requestUpdateSupplierDTO.active() != null
                && requestUpdateSupplierDTO.active() != supplier.isActive()) {

            if (requestUpdateSupplierDTO.active()) {
                supplier.activate();
            } else {
                supplier.desactivate();
            }
        }

        return supplierMapper.toResponseSupplierDTO(supplierRepository.save(supplier));
    }

    @Transactional
    @Override
    public void deleteSupplier(long id) {

        if (!supplierRepository.existsById(id)) throw new EntityNotFoundException("El Proveedor que intenta eliminar no existe");

        supplierRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseSupplierDTO getByIdSupplier(long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor con id " + id + " no existe"));
        return supplierMapper.toResponseSupplierDTO(supplier);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseSupplierDTO> getAllSuppliers() {
        return supplierMapper.toListResponseSupplierDTO(supplierRepository.findAll());
    }


    @Transactional
    @Override
    public void addProductToSupplier(long supplierId, long productId) {


        if (!supplierRepository.existsById(supplierId))
            throw new EntityNotFoundException("El proveedor con id " + supplierId + " no existe");
        if (!productRepository.existsById(productId))
            throw new EntityNotFoundException("El producto con id " + productId + " no existe");
        if (supplierRepository.existsLink(supplierId, productId))
            throw new IllegalArgumentException("El producto ya está asociado a este proveedor");

        try {
            supplierRepository.linkProduct(supplierId, productId);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("El producto ya está asociado a este proveedor");
        }
    }



    @Transactional
    @Override
    public void removeProductFromSupplier(long supplierId, long productId) {

        if (!supplierRepository.existsById(supplierId))
            throw new EntityNotFoundException("El proveedor con id " + supplierId + " no existe");
        if (!productRepository.existsById(productId))
            throw new EntityNotFoundException("El producto con id " + productId + " no existe");

        supplierRepository.unlinkProduct(supplierId, productId);
    }
}
