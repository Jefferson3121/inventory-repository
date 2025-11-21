package com.inventory.inventory_servic.service;

import com.inventory.inventory_servic.component.ProductMapper;
import com.inventory.inventory_servic.dto.RequestChangeCategory;
import com.inventory.inventory_servic.dto.RequestProductDTO;
import com.inventory.inventory_servic.dto.RequestUpdataPrice;
import com.inventory.inventory_servic.dto.ResponseProductDTO;
import com.inventory.inventory_servic.model.Category;
import com.inventory.inventory_servic.model.Product;
import com.inventory.inventory_servic.repository.CategoryRepository;
import com.inventory.inventory_servic.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {

    ProductMapper productMapper;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Transactional
    public ResponseProductDTO create(RequestProductDTO requestProductDTO){

        String nameCategory = requestProductDTO.category();

        Category category = categoryRepository.findByName(nameCategory)
                .orElseThrow(()-> new RuntimeException("Category %s does not exist. Create it first or choose a valid one.".formatted(nameCategory))); // mejorar lo de la excepcion y as porque ebes aclara que el producto que intetas guardar esta siendo creado con una categoria que no existe

        Product product = productMapper.dtoToProduct(requestProductDTO);
        product.setCategory(category);

        Product save = productRepository.save(product);

        return productMapper.toResponseProducDto(save);//lo de las excepciones
    }


    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id); //Manejar excepciones cuando falla (averiguar cuals son esas posibles exepciones
    }


    @Transactional
    public void updatePrice(Long id, RequestUpdataPrice requestUpdataPrice){

        BigDecimal newPrice = requestUpdataPrice.price();

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")); //Mejorar la excepcion si es al correcta o no y cambiar mensaje al igles

        product.setPrice(newPrice);
        productRepository.save(product); //Manejar excepciones y elejir cual es la mejor para lanzar
    }


    @Transactional
    public void changeCategory(long id, RequestChangeCategory requestChangeCategory){

         //dudas de que es lo que llega ya que es un objeto que debe estar creado pero el cliente solo manda el nombre

        String changeCategory = requestChangeCategory.category();

        Category category = categoryRepository.findByName(changeCategory)
                .orElseThrow(() -> new RuntimeException("Category %s does not exist. Create it first or choose a valid one.".formatted(changeCategory)));

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product inecxistente")); //Modificar expcecion por una mas clara e igual el mesaje

        product.setCategory(category);
        productRepository.save(product);
    }
}
