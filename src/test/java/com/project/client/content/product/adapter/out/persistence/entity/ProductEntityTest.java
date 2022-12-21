package com.project.client.content.product.adapter.out.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductEntityTest {

    /**
     * Method under test: {@link ProductEntity#getSimplePrice()}
     */
    @Test
    void testGetSimplePrice2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAvailability(true);
        productEntity.setId("42");
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        assertEquals(10.0d, productEntity.getSimplePrice());
    }
}

