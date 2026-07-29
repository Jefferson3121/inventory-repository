package com.inventory.inventory_servic.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.inventory_servic.dto.response.ResponseSupplierDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private String supplierJson(String name, String phone, String email) {
        return """
            {
              "name": "%s",
              "contactName": "Juan Perez",
              "phone": "%s",
              "email": "%s",
              "address": {
                "street": "Calle 123", "city": "Medellin",
                "state": "Antioquia", "zipCode": "050001", "country": "Colombia"
              }
            }
            """.formatted(name, phone, email);
    }



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



    private long createProduct(String name, long categoryId) throws Exception {
        String json = """
        {
          "name": "%s",
          "netContent": { "value": 1.5, "unit": "KILOGRAM" },
          "categoryId": %d,
          "price": 100.00,
          "description": "Producto test",
          "brand": "Marca Test",
          "stock": { "quantity": 10, "minQuantity": 2 }
        }
        """.formatted(name, categoryId);

        MvcResult result = mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }


    private long createSupplier(String name, String phone, String email) throws Exception {
        MvcResult result = mockMvc.perform(post("/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(supplierJson(name, phone, email)))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }


    @Nested
    @DisplayName("POST /api/suppliers - Crear proveedor")
    class CreateSupplierTests {

        @Test
        void shouldCreateSupplierCorrectly() throws Exception {
            mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(supplierJson("Proveedor Test", "123456", "test@test.com")))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("proveedor test"))
                    .andExpect(jsonPath("$.active").value(true));
        }

        @Test
        void shouldNotCreateSupplierWithoutName() throws Exception {
            String json = """
                { "phone": "123456", "email": "test@test.com",
                  "address": {"street":"x","city":"x","state":"x","zipCode":"x","country":"x"} }
                """;

            mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldNotAllowDuplicateSupplier() throws Exception {
            String json = supplierJson("Proveedor Dup", "999999", "dup@test.com");

            mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }


    @Nested
    @DisplayName("PUT /suppliers/{id} - Actualizar proveedor")
    class UpdateSupplierTests {

        @Test
        void shouldUpdateNameCorrectly() throws Exception {
            MvcResult createResult = mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(supplierJson("Proveedor Original", "111222", "original@test.com")))
                    .andExpect(status().isCreated())
                    .andReturn();

            ResponseSupplierDTO created = objectMapper.readValue(
                    createResult.getResponse().getContentAsString(), ResponseSupplierDTO.class);

            String updateJson = """
                { "name": "Proveedor Actualizado" }
                """;

            MvcResult updateResult = mockMvc.perform(put("/suppliers/{id}", created.id())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isOk())
                    .andReturn();

            ResponseSupplierDTO updated = objectMapper.readValue(
                    updateResult.getResponse().getContentAsString(), ResponseSupplierDTO.class);

            assertNotEquals(created.name(), updated.name());
            assertEquals("proveedor actualizado", updated.name());
            assertEquals(created.phone(), updated.phone());
            assertEquals(created.email(), updated.email());
        }

        @Test
        void shouldReturn404WhenSupplierDoesNotExist() throws Exception {
            String updateJson = """
                { "name": "No importa" }
                """;

            mockMvc.perform(put("/api/suppliers/{id}", 9999L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isNotFound());
        }
    }


    @Nested
    @DisplayName("DELETE /suppliers/{id} - Eliminar proveedor")
    class DeleteSupplierTests {

        @Test
        void shouldDeleteSupplierCorrectly() throws Exception {

            MvcResult createResult = mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(supplierJson("Proveedor Delete", "333444", "delete@test.com")))
                    .andExpect(status().isCreated())
                    .andReturn();

            long id = objectMapper.readTree(createResult.getResponse().getContentAsString()).get("id").asLong();

            mockMvc.perform(delete("/suppliers/{id}", id))
                    .andExpect(status().isNoContent());
        }

        @Test
        void shouldReturn404WhenDeletingNonExistentSupplier() throws Exception {
            mockMvc.perform(delete("/suppliers/{id}", 9999L))
                    .andExpect(status().isNotFound());
        }


    }


    @Nested
    @DisplayName("GET /suppliers/{id} - Buscar por id")
    class GetByIdSupplierTests {

        @Test
        void shouldReturnSupplierWithRequestedId() throws Exception {

            MvcResult createResult = mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(supplierJson("Proveedor GetById", "444555", "getbyid@test.com")))
                    .andExpect(status().isCreated())
                    .andReturn();

            String createdJson = createResult.getResponse().getContentAsString();

            long id = objectMapper.readTree(createdJson).get("id").asLong();

            mockMvc.perform(get("/suppliers/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(content().json(createdJson, false));
        }


        @Test
        void shouldReturn404WhenSupplierDoesNotExist() throws Exception {
            mockMvc.perform(get("/suppliers/{id}", 9999L))
                    .andExpect(status().isNotFound());
        }
    }


    @Nested
    @DisplayName("GET /suppliers - Listar todos")
    class GetAllSuppliersTests {


        @Test
        void shouldReturnEmptyListWhenNoSuppliersExist() throws Exception {
            mockMvc.perform(get("/suppliers"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isEmpty());
        }



        @Test
        void shouldReturnAllCreatedSuppliers() throws Exception {

            mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(supplierJson("Proveedor Uno", "111111", "uno@test.com")))
                    .andExpect(status().isCreated());

            mockMvc.perform(post("/suppliers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(supplierJson("Proveedor Dos", "222222", "dos@test.com")))
                    .andExpect(status().isCreated());

            mockMvc.perform(get("/suppliers"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[*].name", containsInAnyOrder("proveedor uno", "proveedor dos")));
        }



    }




    @Nested
    @DisplayName("POST /suppliers/{supplierId}/products/{productId} - Asociar producto")
    class AddProductToSupplierTests {

        @Test
        void shouldAddProductToSupplier() throws Exception {
            long categoryId = createCategory("Categoria Add");
            long productId = createProduct("Producto Add", categoryId);
            long supplierId = createSupplier("Proveedor Add", "777111", "add@test.com");

            mockMvc.perform(post("/suppliers/{supplierId}/products/{productId}", supplierId, productId))
                    .andExpect(status().isNoContent());
        }

        @Test
        void shouldReturn404WhenSupplierDoesNotExist() throws Exception {
            long categoryId = createCategory("Categoria Add2");
            long productId = createProduct("Producto Add2", categoryId);

            mockMvc.perform(post("/suppliers/{supplierId}/products/{productId}", 9999L, productId))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturn404WhenProductDoesNotExist() throws Exception {
            long supplierId = createSupplier("Proveedor Add3", "777222", "add3@test.com");

            mockMvc.perform(post("/suppliers/{supplierId}/products/{productId}", supplierId, 9999L))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldNotAllowDuplicateLink() throws Exception {
            long categoryId = createCategory("Categoria Add4");
            long productId = createProduct("Producto Add4", categoryId);
            long supplierId = createSupplier("Proveedor Add4", "777333", "add4@test.com");

            mockMvc.perform(post("/suppliers/{supplierId}/products/{productId}", supplierId, productId))
                    .andExpect(status().isNoContent());

            mockMvc.perform(post("/suppliers/{supplierId}/products/{productId}", supplierId, productId))
                    .andExpect(status().isBadRequest());
        }
    }






    @Nested
    @DisplayName("DELETE /suppliers/{supplierId}/products/{productId} - Desasociar producto")
    class RemoveProductFromSupplierTests {

        @Test
        void shouldRemoveProductFromSupplier() throws Exception {
            long categoryId = createCategory("Categoria Remove");
            long productId = createProduct("Producto Remove", categoryId);
            long supplierId = createSupplier("Proveedor Remove", "888111", "remove@test.com");

            mockMvc.perform(post("/suppliers/{supplierId}/products/{productId}", supplierId, productId))
                    .andExpect(status().isNoContent());

            mockMvc.perform(delete("/suppliers/{supplierId}/products/{productId}", supplierId, productId))
                    .andExpect(status().isNoContent());
        }



        @Test
        void shouldReturnNoContentWhenLinkDoesNotExist() throws Exception {
            long categoryId = createCategory("Categoria Remove2");
            long productId = createProduct("Producto Remove2", categoryId);
            long supplierId = createSupplier("Proveedor Remove2", "888222", "remove2@test.com");

            // nunca se asoció, igual debe devolver 204 (idempotente, opción A elegida)
            mockMvc.perform(delete("/suppliers/{supplierId}/products/{productId}", supplierId, productId))
                    .andExpect(status().isNoContent());
        }

        @Test
        void shouldReturn404WhenSupplierDoesNotExist() throws Exception {
            long categoryId = createCategory("Categoria Remove3");
            long productId = createProduct("Producto Remove3", categoryId);

            mockMvc.perform(delete("/suppliers/{supplierId}/products/{productId}", 9999L, productId))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturn404WhenProductDoesNotExist() throws Exception {
            long supplierId = createSupplier("Proveedor Remove4", "888333", "remove4@test.com");

            mockMvc.perform(delete("/suppliers/{supplierId}/products/{productId}", supplierId, 9999L))
                    .andExpect(status().isNotFound());
        }
    }
}