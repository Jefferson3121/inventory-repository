package com.inventory.inventory_servic.application.usecase;

import com.inventory.inventory_servic.infrastructure.adapter.in.rest.mapper.ProductMapper;
import com.inventory.inventory_servic.application.dto.request.RequestProductDTO;
import com.inventory.inventory_servic.application.dto.response.ResponseProductDTO;
import com.inventory.inventory_servic.domain.model.Category;
import com.inventory.inventory_servic.domain.model.Product;
import com.inventory.inventory_servic.domain.model.ProductType;
import com.inventory.inventory_servic.infrastructure.adapter.out.persistence.CategoryRepository;
import com.inventory.inventory_servic.infrastructure.adapter.out.persistence.ProductRepository;
import com.inventory.inventory_servic.domain.port.in.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductMapper productMapper;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;


    }
