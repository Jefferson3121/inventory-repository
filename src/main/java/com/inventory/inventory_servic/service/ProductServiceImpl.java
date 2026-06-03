package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.component.ProductMapper;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductStockDTO;
import com.inventory.inventory_servic.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO) {

        Product product = productRepository.save(productMapper.toProduc(requestProductDTO));
        return productMapper.toResponseProduct(product);
    }

    @Override
    public ResponseProductDTO updateProduct(RequestUpdateProductDTO requestUpdateProductDTO) {
        Product product = productRepository.save(productMapper.toUpdateProduct(requestUpdateProductDTO));
        return productMapper.toResponseProduct(product);
    }

    @Override
    public void deleteProduct(long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public ResponseProductDTO getByIdProduct(long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product con id "));
        return productMapper.toResponseProduct(product);
    }

    @Override
    public List<ResponseProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponseProduct)
                .toList();
    }



    @Override
    public List<ResponseProductStockDTO> getAllStock(){
        return productRepository.findAll().stream()
                .map(productMapper::toProductStockResponseDTO)
                .toList();
    }
}
