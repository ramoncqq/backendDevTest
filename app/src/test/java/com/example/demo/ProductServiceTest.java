package com.example.demo;

import com.example.demo.model.ProductDTO;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.exception.NotFoundException;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import com.example.demo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private IProductRepository productRepository;

    public static final String BASIC_ID = "PRODUCTO-001";

    @Test
    public void whenProductsById_OK() {
        Mockito.when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(this.initProduct()));
        ProductDTO response = productService.getProductById(UUID.randomUUID().toString());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getId(), BASIC_ID);
    }

    @Test
    public void whenProductsById_fail() {
        Mockito.when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        try {
            productService.getProductById(UUID.randomUUID().toString());
        } catch (NotFoundException notFoundException) {
            assertSame(notFoundException.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void whenProductsSimilarById_OK() {
        Mockito.when(productRepository.findByIdContainingOrderById(Mockito.anyString()))
                .thenReturn(Collections.singletonList(this.initProduct()));
        List<String> response = productService.getProductSimilar(UUID.randomUUID().toString());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.size(), 1);
        Assertions.assertEquals(response.get(0), BASIC_ID);
    }


    private ProductEntity initProduct() {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(BASIC_ID);
        productEntity.setAvailability(Boolean.FALSE);
        productEntity.setPrice(BigDecimal.valueOf(500.25D));
        productEntity.setName("Nombre del Producto");

        return productEntity;
    }

}
