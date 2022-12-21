package com.project.client.content.product.adapter.out.persistence;

import com.project.client.content.product.adapter.out.persistence.entity.ProductEntity;
import com.project.client.content.product.adapter.out.persistence.entity.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductRepository.class})
@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {
    @MockBean
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Method under test: {@link ProductRepository#getBySimilarity(String)}
     */
    @Test
    void testGetBySimilarity() throws IOException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAvailability(true);
        productEntity.setId("42");
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productJpaRepository.findAll()).thenReturn(new ArrayList<>());
        when(productJpaRepository.findById((String) any())).thenReturn(ofResult);
        productRepository.getBySimilarity("42");
    }

    /**
     * Method under test: {@link ProductRepository#getBySimilarity(String)}
     */
    @Test
    void testGetBySimilarity2() throws IOException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAvailability(true);
        productEntity.setId("42");
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productJpaRepository.findAll()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(productJpaRepository.findById((String) any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> productRepository.getBySimilarity("42"));
        verify(productJpaRepository).findAll();
        verify(productJpaRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ProductRepository#getBySimilarity(String)}
     */
    @Test
    void testGetBySimilarity3() throws IOException {
        when(productJpaRepository.findAll()).thenReturn(new ArrayList<>());
        when(productJpaRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productRepository.getBySimilarity("42"));
        verify(productJpaRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ProductRepository#get(String)}
     */
    @Test
    void testGet() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAvailability(true);
        productEntity.setId("42");
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productJpaRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(productEntity, productRepository.get("42"));
        verify(productJpaRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ProductRepository#get(String)}
     */
    @Test
    void testGet2() {
        when(productJpaRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productRepository.get("42"));
        verify(productJpaRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ProductRepository#get(String)}
     */
    @Test
    void testGet3() {
        when(productJpaRepository.findById((String) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> productRepository.get("42"));
        verify(productJpaRepository).findById((String) any());
    }
}

