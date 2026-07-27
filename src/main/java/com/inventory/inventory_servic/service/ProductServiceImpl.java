package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.component.ProductMapper;
import com.inventory.inventory_servic.domain.Category;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductStockDTO;
import com.inventory.inventory_servic.repository.CategoryRepository;
import com.inventory.inventory_servic.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;


    @Transactional
    @Override
    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO) {

        String normalizedName = Product.normalize(requestProductDTO.name());
        String normalizedBrand = Product.normalize(requestProductDTO.brand());

        if (productRepository.existsByNameAndBrandAndNetContent_ValueAndNetContent_Unit(
                normalizedName, normalizedBrand,
                requestProductDTO.netContent().value(), requestProductDTO.netContent().unit())) {
            throw new IllegalArgumentException("Ya existe un producto con ese nombre, marca y contenido neto");
        }



        Category category = categoryRepository.findById(requestProductDTO.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría con id " + requestProductDTO.categoryId() + " no existe"));

        Product product = productMapper.toProduct(requestProductDTO, category);

        return productMapper.toResponseProduct(productRepository.save(product));
    }




    @Transactional
    @Override
    public ResponseProductDTO updateProduct(long id, RequestUpdateProductDTO requestUpdateProductDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto con id " + id + " no existe"));

        if (requestUpdateProductDTO.name() != null)
            product.updateName(requestUpdateProductDTO.name());

        if (requestUpdateProductDTO.description() != null)
            product.updateDescription(requestUpdateProductDTO.description());

        if (requestUpdateProductDTO.price() != null)
            product.updatePrice(requestUpdateProductDTO.price());

        if (requestUpdateProductDTO.netContent() != null)
            product.updateNetContent(productMapper.toNetContent(requestUpdateProductDTO.netContent()));

        if (requestUpdateProductDTO.categoryId() > 0) {
            Category category = categoryRepository.findById(requestUpdateProductDTO.categoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria no existe"));
            product.updateCategory(category);
        }


        return productMapper.toResponseProduct(productRepository.save(product));

    }




    @Transactional
    @Override
    public void deleteProduct(long idProduct) {
        productRepository.deleteById(idProduct);
    }




    @Transactional(readOnly = true)
    @Override
    public ResponseProductDTO getByIdProduct(long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product con id "));
        return productMapper.toResponseProduct(product);
    }




    @Transactional(readOnly = true)
    @Override
    public List<ResponseProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponseProduct)
                .toList();
    }





    @Transactional(readOnly = true)
    @Override
    public List<ResponseProductStockDTO> getAllStock(){
        return productRepository.findAll().stream()
                .map(productMapper::toProductStockResponseDTO)
                .toList();
    }



}
