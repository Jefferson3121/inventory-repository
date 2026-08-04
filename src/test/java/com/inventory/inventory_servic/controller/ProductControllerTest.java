package com.inventory.inventory_servic.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.inventory_servic.repository.CategoryRepository;
import com.inventory.inventory_servic.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private Environment environment;

    @Autowired
    private ConfigurableApplicationContext context;



    @Autowired
    private ConfigurableEnvironment environmentt;



    private long createCategory(String name) throws Exception {
        String json = """
        { "name": "%s", "description": "Descripcion test", "idParentCategory": 0 }
        """.formatted(name);

        MvcResult result = mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }



    private Map<String, Object> validProductFields(long categoryId) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("name", "Producto Test");
        fields.put("netContent", Map.of("value", 1.5, "unit", "KILOGRAM"));
        fields.put("categoryId", categoryId);
        fields.put("price", new BigDecimal("100.00"));
        fields.put("description", "Descripcion test");
        fields.put("brand", "Marca Test");
        fields.put("stock", Map.of("quantity", 10, "minQuantity", 2));
        return fields;
    }



    @Nested
    @DisplayName("POST /product - Crear producto")
    class CreateProductTests {



        @Test
        void shouldCreateProductCorrectly() throws Exception {





            long categoryId = createCategory("Categoria Product Create");
            String json = objectMapper.writeValueAsString(validProductFields(categoryId));

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.netContent.value").value(1.5))
                    .andExpect(jsonPath("$.netContent.unit").value("KILOGRAM"))
                    .andExpect(jsonPath("$.stock.quantity").value(10))
                    .andExpect(jsonPath("$.stock.minQuantity").value(2));
        }



        @ParameterizedTest
        @ValueSource(strings = {"name", "netContent", "categoryId", "price", "brand", "stock"})
        void shouldFailWhenRequiredFieldIsMissing(String fieldToRemove) throws Exception {
            long categoryId = createCategory("Categoria Product Missing");

            Map<String, Object> fields = validProductFields(categoryId);
            fields.remove(fieldToRemove);

            String json = objectMapper.writeValueAsString(fields);

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldFailWhenCategoryDoesNotExist() throws Exception {
            Map<String, Object> fields = validProductFields(9999);
            String json = objectMapper.writeValueAsString(fields);

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldFailWhenPriceIsNegative() throws Exception {
            long categoryId = createCategory("Categoria Product NegPrice");

            Map<String, Object> fields = validProductFields(categoryId);
            fields.put("price", new BigDecimal("-10.00"));

            String json = objectMapper.writeValueAsString(fields);

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldNotAllowDuplicateProduct() throws Exception {
            long categoryId = createCategory("Categoria Product Dup");
            String json = objectMapper.writeValueAsString(validProductFields(categoryId));

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }




    @Nested
    @DisplayName("PATCH /product/{id} - Actualizar producto")
    class UpdateProductTests {

        @Test
        void shouldUpdateNameCorrectly() throws Exception {
            long categoryId = createCategory("Categoria Update Name10876");
            String createJson = objectMapper.writeValueAsString(validProductFields(categoryId));

            MvcResult createResult = mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            long productId = objectMapper.readTree(createResult.getResponse().getContentAsString()).get("id").asLong();

            String updateJson = """
            { "name": "Producto Actualizado", "categoryId": 0 }
            """;

            mockMvc.perform(patch("/product/{id}", productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Producto Actualizado"))
                    .andExpect(jsonPath("$.brand").value("marca test"))
                    .andExpect(jsonPath("$.nameCategory").value("Categoria Update Name10876"));
        }

        @Test
        void shouldUpdatePriceCorrectly() throws Exception {
            long categoryId = createCategory("Categoria Update Price");
            String createJson = objectMapper.writeValueAsString(validProductFields(categoryId));

            MvcResult createResult = mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            long productId = objectMapper.readTree(createResult.getResponse().getContentAsString()).get("id").asLong();

            String updateJson = """
            { "price": 250.00 }
            """;

            mockMvc.perform(patch("/product/{id}", productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(250.00));
        }

        @Test
        void shouldUpdateCategoryCorrectly() throws Exception {
            long originalCategoryId = createCategory("Categoria Original");
            long newCategoryId = createCategory("Categoria Nueva");

            String createJson = objectMapper.writeValueAsString(validProductFields(originalCategoryId));

            MvcResult createResult = mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            long productId = objectMapper.readTree(createResult.getResponse().getContentAsString()).get("id").asLong();

            String updateJson = """
            { "categoryId": %d }
            """.formatted(newCategoryId);

            mockMvc.perform(patch("/product/{id}", productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nameCategory").value("Categoria Nueva"));
        }

        @Test
        void shouldReturn404WhenCategoryDoesNotExistOnUpdate() throws Exception {
            long categoryId = createCategory("Categoria Update Invalid");
            String createJson = objectMapper.writeValueAsString(validProductFields(categoryId));

            MvcResult createResult = mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            long productId = objectMapper.readTree(createResult.getResponse().getContentAsString()).get("id").asLong();

            String updateCategory = """
            { "categoryId": 9999 }
            """;

            mockMvc.perform(patch("/product/{id}", productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateCategory))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturn404WhenProductDoesNotExist() throws Exception {
            String updateJson = """
            { "categoryId": 0, "name": "No importa" }
            """;

            mockMvc.perform(patch("/product/{id}", 9999L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isNotFound());
        }
    }




    @Nested
    @DisplayName("DELETE /product/{id} - Eliminar producto")
    class DeleteProductTests {

        @Test
        void shouldDeleteProductCorrectly() throws Exception {
            long categoryId = createCategory("Categoria Delete");
            String createJson = objectMapper.writeValueAsString(validProductFields(categoryId));

            MvcResult createResult = mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            long productId = objectMapper.readTree(createResult.getResponse().getContentAsString()).get("id").asLong();

            mockMvc.perform(delete("/product/{id}", productId))
                    .andExpect(status().isNoContent());
        }

        @Test
        void shouldReturnNoContentWhenProductDoesNotExist() throws Exception {
            // idempotente: borrar algo que no existe también devuelve 204
            mockMvc.perform(delete("/product/{id}", 9999L))
                    .andExpect(status().isNoContent());
        }
    }


    @Nested
    @DisplayName("GET /product/{id} - Buscar producto por id")
    class GetByIdProductTests {

        @Test
        void shouldReturnProductWithRequestedId() throws Exception {
            long categoryId = createCategory("Categoria GetById");
            String createJson = objectMapper.writeValueAsString(validProductFields(categoryId));

            MvcResult createResult = mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            String createdJson = createResult.getResponse().getContentAsString();
            long productId = objectMapper.readTree(createdJson).get("id").asLong();

            mockMvc.perform(get("/product/{id}", productId))
                    .andExpect(status().isOk())
                    .andExpect(content().json(createdJson, false));
        }

        @Test
        void shouldReturn404WhenProductDoesNotExist() throws Exception {
            mockMvc.perform(get("/product/{id}", 9999))
                    .andExpect(status().isNotFound());
        }
    }



    @Nested
    @DisplayName("GET /product/all - Listar todos los productos")
    class GetAllProductsTests {

        @Test
        void shouldReturnEmptyListWhenNoProductsExist() throws Exception {
            mockMvc.perform(get("/product/all"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void shouldReturnAllCreatedProducts() throws Exception {
            long categoryId = createCategory("Categoria GetAll");

            Map<String, Object> product1 = validProductFields(categoryId);
            product1.put("name", "Producto Uno");

            Map<String, Object> product2 = validProductFields(categoryId);
            product2.put("name", "Producto Dos");
            product2.put("brand", "Marca Distinta");

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(product1)))
                    .andExpect(status().isCreated());



            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(product2)))
                    .andExpect(status().isCreated());



            mockMvc.perform(get("/product/all"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[*].name", containsInAnyOrder("producto uno", "producto dos")));
        }
    }




    @Nested
    @DisplayName("GET /product/stock - Listar stock de productos")
    class GetAllStockTests {

        @Test
        void shouldReturnEmptyListWhenNoProductsExist() throws Exception {
            mockMvc.perform(get("/product/stock"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }


        @Test
        void shouldReturnStockOfCreatedProducts() throws Exception {
            long categoryId = createCategory("Categoria Stock");

            Map<String, Object> product = validProductFields(categoryId);
            product.put("name", "product stock");
            product.put("stock", Map.of("quantity", 25, "minQuantity", 5));


            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(product)))
                    .andExpect(status().isCreated());


            mockMvc.perform(get("/product/stock"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].name").value("product stock"))
                    .andExpect(jsonPath("$[0].quantity").value(25))
                    .andExpect(jsonPath("$[0].minQuantity").value(5));
        }
    }
}
