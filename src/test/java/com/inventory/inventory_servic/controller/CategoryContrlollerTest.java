package com.inventory.inventory_servic.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.inventory_servic.repository.CategoryRepository;
import com.inventory.inventory_servic.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryContrlollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    private long createCategory(String name) throws Exception {
        String json = """
        {
         "name": "%s",
         "description": "Descripcion test"
         }
        """.formatted(name);

        MvcResult result = mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }


    @Nested
    @DisplayName("POST /category - Crear categoria")
    class CreateCategoryTests {

        @Test
        void shouldCreateCategoryCorrectly() throws Exception {
            String json = """
                    { "name": "Categoria Test", "description": "Descripcion test" }
                    """;

            mockMvc.perform(post("/category")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("Categoria Test"))
                    .andExpect(jsonPath("$.active").value(false));
        }


        @ParameterizedTest
        @ValueSource(strings = {"name", "description"})
        void shouldFailWhenRequiredFieldIsMissing(String fieldToRemove) throws Exception {
            Map<String, Object> fields = new LinkedHashMap<>();
            fields.put("name", "Categoria Missing");
            fields.put("description", "Descripcion test");
            fields.put("idParentCategory", 0);

            fields.remove(fieldToRemove);

            String json = objectMapper.writeValueAsString(fields);

            mockMvc.perform(post("/category")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldNotAllowDuplicateCategoryName() throws Exception {
            String json = """
                    { "name": "Categoria Duplicada", "description": "Descripcion test", "idParentCategory": 0 }
                    """;

            mockMvc.perform(post("/category")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            mockMvc.perform(post("/category")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isConflict());
        }



        @Test
        void shouldFailWhenParentCategoryDoesNotExist() throws Exception {
            String json = """
                    { "name": "Categoria Hija", "description": "Descripcion test", "idParentCategory": 9999 }
                    """;

            mockMvc.perform(post("/category")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldCreateSubcategoryWithValidParent() throws Exception {
            long parentId = createCategory("Categoria Padre");

            String json = """
                    { "name": "Categoria Hija Valida", "description": "Descripcion test", "idParentCategory": %d }
                    """.formatted(parentId);

            mockMvc.perform(post("/category")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());
        }
    }










    @Nested
    @DisplayName("PATCH /category/{id} - Actualizar categoria")
    class UpdateCategoryTests {

        @Test
        void shouldUpdateNameCorrectly() throws Exception {
            long categoryId = createCategory("Categoria Update Name");

            String updateJson = """
            { "name": "Categoria Actualizada" }
            """;

            mockMvc.perform(patch("/category/{id}", categoryId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Categoria Actualizada"));
        }

        @Test
        void shouldActivateCategoryWhenActiveIsTrue() throws Exception {
            long categoryId = createCategory("Categoria Activar");


            mockMvc.perform(patch("/category/{id}", categoryId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"active\": false }"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.active").value(false));


            mockMvc.perform(patch("/category/{id}", categoryId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"active\": true }"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.active").value(true));
        }

        @Test
        void shouldDeactivateCategoryWhenActiveIsFalse() throws Exception {
            long categoryId = createCategory("Categoria Desactivar");

            mockMvc.perform(patch("/category/{id}", categoryId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"active\": false }"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.active").value(false));
        }

        @Test
        void shouldUpdateParentCategoryCorrectly() throws Exception {
            long parentId = createCategory("Categoria Padre Update");
            long categoryId = createCategory("Categoria Hija Update");

            String updateJson = """
            { "parentCategoryId": %d }
            """.formatted(parentId);

            mockMvc.perform(patch("/category/{id}", categoryId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isOk());
        }




        @Test
        void shouldFailWhenNewParentCategoryDoesNotExist() throws Exception {
            long categoryId = createCategory("Categoria Update Invalid Parent");

            String updateJson = """
        { "parentCategoryId": 9999 }
        """;

            mockMvc.perform(patch("/category/{id}", categoryId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isBadRequest()); // IllegalArgumentException -> 400
        }





        @Test
        void shouldReturn404WhenCategoryDoesNotExist() throws Exception {
            String updateJson = """
            { "name": "No importa" }
            """;

            mockMvc.perform(patch("/category/{id}", 9999L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateJson))
                    .andExpect(status().isNotFound());
        }
    }






    @Nested
    @DisplayName("DELETE /category/{id} - Eliminar categoria")
    class DeleteCategoryTests {

        @Test
        void shouldDeleteCategoryCorrectly() throws Exception {
            long categoryId = createCategory("Categoria Delete");

            mockMvc.perform(delete("/category/{id}", categoryId))
                    .andExpect(status().isNoContent());
        }

        @Test
        void shouldReturn404WhenCategoryDoesNotExist() throws Exception {
            mockMvc.perform(delete("/category/{id}", 9999L))
                    .andExpect(status().isNotFound());
        }
    }





    @Nested
    @DisplayName("GET /category/{id} - Buscar categoria por id")
    class GetCategoryTests {

        @Test
        void shouldReturnCategoryWithRequestedId() throws Exception {
            long categoryId = createCategory("Categoria GetById");

            String createdJson = mockMvc.perform(get("/category/{id}", categoryId))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            mockMvc.perform(get("/category/{id}", categoryId))
                    .andExpect(status().isOk())
                    .andExpect(content().json(createdJson, false));
        }

        @Test
        void shouldReturn404WhenCategoryDoesNotExist() throws Exception {
            mockMvc.perform(get("/category/{id}", 9999L))
                    .andExpect(status().isNotFound());
        }
    }






    @Nested
    @DisplayName("GET /category/all - Listar todas las categorias")
    class GetAllCategoriesTests {

        @Test
        void shouldReturnEmptyListWhenNoCategoriesExist() throws Exception {
            mockMvc.perform(get("/category/all"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void shouldReturnAllCreatedCategories() throws Exception {
            createCategory("Categoria Padre GetAll");
            createCategory("Categoria 1");
            createCategory("Categoria 2");
            createCategory("ategoria 3");
            createCategory("Categoria 4");
            createCategory("Categoria 5");





            mockMvc.perform(get("/category/all"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(6))
                    .andExpect(jsonPath("$[*].name", containsInAnyOrder(
                    "Categoria Padre GetAll",
                    "Categoria 1",
                    "Categoria 2",
                    "ategoria 3",
                    "Categoria 4",
                    "Categoria 5"
            )));   }
    }



}
