package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateDescriptionDTO;

public interface CategoryService {

    public void addCategory(RequestCategoryDTO categoryDTO);

    public void updateDescription(long id, RequestUpdateDescriptionDTO newDescription);

    public void activateCategory(long id);

    public void deleteCategory(long id);
}
