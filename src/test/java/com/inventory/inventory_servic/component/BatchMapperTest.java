package com.inventory.inventory_servic.component;


import com.inventory.inventory_servic.domain.*;
import com.inventory.inventory_servic.dto.request.RequestBatchDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;



import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BatchMapperTest {

    private BatchMapper batchMapper;


    private Product createProduct(String name, BigDecimal price) throws NoSuchFieldException, IllegalAccessException{


        NetContent netContent = new NetContent(new BigDecimal(200), UnitMeasurement.GRAM);

        Category category = Category.createCategory("Cualquier categoria", "Categria para test" );

        Product product = Product.createProduct(name, netContent,category,price,  "marca-test");

        Field field = Product.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(product, 12);

        Field createDate = Product.class.getDeclaredField("createdAt");
        createDate.setAccessible(true);
        createDate.set(product, LocalDateTime.of(2020, 8, 23, 22, 40, 45));

        Field updateAtField = Product.class.getDeclaredField("updatedAt");
        updateAtField.setAccessible(true);
        updateAtField.set(product, LocalDateTime.of(2021, 3, 23, 12, 34, 12));


        return product;


    }


    @Test
    public void shouldCreateCorrectBatchWithInputData() throws IllegalAccessException, NoSuchFieldException{

        Product product = createProduct("product tes", new BigDecimal(2300));




        LocalDate expirationDate = LocalDate.of(2027, 12, 31);
        LocalDate manufacturingDate = LocalDate.of(2026, 8, 17);
        BigDecimal quantity = new BigDecimal("50");

        RequestBatchDTO dto = new RequestBatchDTO(
                product.getId(),
                expirationDate,
                manufacturingDate,
                quantity
        );



        Batch batch = batchMapper.toBatch(dto, product);


        assertAll(
                () -> assertEquals(batch.getProduct(), product),
                () -> assertEquals(batch.getExpirationDate(), expirationDate),
                () -> assertEquals(batch.getManufacturingDate(), manufacturingDate)
        );

    }
}
