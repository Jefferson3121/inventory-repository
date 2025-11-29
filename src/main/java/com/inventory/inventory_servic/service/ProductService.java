package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestChangeCategory;
import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdataPrice;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.model.ProductType;

public interface ProductService {

    public ResponseProductDTO create(RequestProductDTO requestProductDTO);
    public void delete(Long id);
    public void updatePrice(Long id, RequestUpdataPrice requestUpdataPrice);
    public void changeCategory(long id, RequestChangeCategory requestChangeCategory);
    public void changeType(long id, ProductType type);
    public void getProduct(long id);
    public void getAllProducts();
}
