package com.inventory.inventory_servic.application.usecase;

import com.inventory.inventory_servic.infrastructure.adapter.in.rest.mapper.CategoryMapper;
import com.inventory.inventory_servic.application.dto.request.RequestCategoryDTO;
import com.inventory.inventory_servic.application.dto.request.RequestUpdateDescriptionDTO;
import com.inventory.inventory_servic.application.dto.response.ResponseCategoryDTO;
import com.inventory.inventory_servic.domain.model.Category;
import com.inventory.inventory_servic.infrastructure.adapter.out.persistence.CategoryRepository;
import com.inventory.inventory_servic.domain.port.in.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;


}
