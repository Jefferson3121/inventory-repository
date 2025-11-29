package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.dto.request.RequestUpdateDescriptionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Transactional
    @Override
    public void addCategory(RequestCategoryDTO categoryDTO){

    }

    @Override
    public void updateDescription(long id, RequestUpdateDescriptionDTO newDescription){

    }

    @Override
    public void activateCategory(long id){

    }

    @Override
    public void deleteCategory(long id){

    }
}
