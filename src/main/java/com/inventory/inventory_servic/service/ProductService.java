package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestChangeCategory;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdataPrice;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.model.Product;
import com.inventory.inventory_servic.model.ProductType;

import java.util.List;

public interface ProductService {

    public ResponseProductDTO create(RequestProductDTO requestProductDTO);
    public void delete(Long id);
    public void updatePrice(Long id, RequestUpdataPrice requestUpdataPrice);
    public void changeCategory(Long id, RequestChangeCategory requestChangeCategory);
    public void changeType(Long id, ProductType type);
    public ResponseProductDTO getProduct(Long id);
    public List<Product> getAllProducts();
}
