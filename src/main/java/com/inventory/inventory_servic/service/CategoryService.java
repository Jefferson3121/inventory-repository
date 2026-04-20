package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateDescriptionDTO;
import com.inventory.inventory_servic.dto.response.ResponseCategoryDTO;

public interface CategoryService {

    public ResponseCategoryDTO addCategory(RequestCategoryDTO categoryDTO);

    public void updateDescription(Long id, RequestUpdateDescriptionDTO newDescription);

    public void activateCategory(Integer id);

    public void deleteCategory(Long id);
}
