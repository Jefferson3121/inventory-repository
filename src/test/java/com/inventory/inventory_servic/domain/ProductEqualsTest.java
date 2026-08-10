package com.inventory.inventory_servic.domain;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ProductEqualsTest {

    @Test
    void shouldBeEqualWhenSameId() throws Exception {
        Product p1 = createProductWithId(5L);
        Product p2 = createProductWithId(5L);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentId() throws Exception {
        Product p1 = createProductWithId(5L);
        Product p2 = createProductWithId(7L);

        assertNotEquals(p1, p2);
    }

    @Test
    void shouldNotBeEqualWhenBothIdsAreZero() throws Exception {
        Product p1 = createProductWithId(0L);
        Product p2 = createProductWithId(0L);

        assertNotEquals(p1, p2);
    }

    @Test
    void shouldBeEqualToItself() throws Exception {
        Product p1 = createProductWithId(5L);
        assertEquals(p1, p1);
    }


    private Product createProductWithId(long id) throws Exception {
        Product product = new Product();
        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, id);
        return product;
    }
}