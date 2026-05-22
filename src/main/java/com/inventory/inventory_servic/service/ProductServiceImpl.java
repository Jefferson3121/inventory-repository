package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO){

       return productRepository.save(requestProductDTO);
    }

    @Override
    public ResponseProductDTO updateProduct(RequestUpdateProductDTO requestUpdateProductDTO){

        return productRepository.save(requestUpdateProductDTO);
    }

    public void deleteProduct(long idProduct){

        productRepository.deleteById(idProduct);
    }

    @Override
    public ResponseProductDTO getByIdProduct(long idProduct){

        return productRepository.findById(idProduct)
                .orElseThrow( () -> new EntityNotFoundException("Product con id "));
    }

    @Override
    public List<ResponseProductDTO> getAllProducts(){

        return productRepository.findAll();
    }
}
