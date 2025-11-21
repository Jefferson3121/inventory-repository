package com.inventory.inventory_servic.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class ProductValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void productoInvalidoDebeGenerarErrores() {
        //Crear producto con datos inválidos
        Product producto = new Product();
        producto.setName("");
        producto.setCategory(null);
        producto.setPrice(null);
        producto.setQuantityAvailable(0L);
        producto.setSupplier(null);

        Set<ConstraintViolation<Product>> errores = validator.validate(producto);

        errores.forEach(error ->
                System.out.println(error.getPropertyPath() + ": " + error.getMessage())
        );

        assertFalse(errores.isEmpty());
    }
}
