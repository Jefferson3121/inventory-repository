package com.inventory.inventory_servic.service;


import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;

import java.util.List;

public interface CategoryService {

    public ResponseCategoryDTO createCategory(RequestCategoryDTO requestCategory);

    public ResponseCategoryDTO updateCategory(long id, RequestCategoryDTO requestCategoryDTO);

    public ResponseCategoryDTO getCategory(long idCategory);

    public void deleteCategory(long idCategory);

    public List<ResponseCategoryDTO> getAllCategorys();






}
