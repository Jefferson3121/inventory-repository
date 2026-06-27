package com.inventory.inventory_servic.service;


import com.inventory.inventory_servic.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.dto.response.ResponseProductStockDTO;


import java.util.List;


public interface ProductService {

    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO);

    public ResponseProductDTO updateProduct(long id, RequestUpdateProductDTO requestUpdateProductDTO);

    public void deleteProduct(long idProduct);

    public ResponseProductDTO getByIdProduct(long idProduct);

    public List<ResponseProductDTO> getAllProducts();

    public List<ResponseProductStockDTO> getAllStock();



}
