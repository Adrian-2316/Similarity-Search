package com.project.client.content.product.service;

import com.project.client.content.product.service.ports.out.ProductRepositoryPort;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private ProductRepositoryPort productRepositoryPort;

    @Autowired
    private ProductService productService;

    /**
     * Method under test: {@link ProductService#getBySimilarity(String)}
     */
    @Test
    void testGetBySimilarity() throws IOException, ParseException {
        ArrayList<String> stringList = new ArrayList<>();
        when(productRepositoryPort.getBySimilarity(any())).thenReturn(stringList);
        List<String> actualBySimilarity = productService.getBySimilarity("42");
        assertSame(stringList, actualBySimilarity);
        assertTrue(actualBySimilarity.isEmpty());
        verify(productRepositoryPort).getBySimilarity(any());
    }

    /**
     * Method under test: {@link ProductService#getBySimilarity(String)}
     */
    @Test
    void testGetBySimilarity2() throws IOException, ParseException {
        when(productRepositoryPort.getBySimilarity(any())).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> productService.getBySimilarity("42"));
        verify(productRepositoryPort).getBySimilarity(any());
    }
}

