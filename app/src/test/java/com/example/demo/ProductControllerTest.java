package com.example.demo;

import com.example.demo.controller.ProductController;
import com.example.demo.model.ProductDTO;
import com.example.demo.model.exception.NotFoundException;
import com.example.demo.service.IProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private IProductService productService;

    @Test
    public void whenProductsById_OK() {
        ProductDTO result = this.initProduct();
        Mockito.when(productService.getProductById(Mockito.anyString())).thenReturn(result);
        ResponseEntity<ProductDTO> response = productController.getProductById(UUID.randomUUID().toString());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), result);
    }

    @Test
    public void whenProductsById_fail() {
        Mockito.when(productService.getProductById(Mockito.anyString())).thenThrow(new NotFoundException("product no encontrado"));
        try {
            productController.getProductById(UUID.randomUUID().toString());
        } catch (NotFoundException notFoundException) {
            assertSame(notFoundException.getClass(), NotFoundException.class);
        }

    }

    @Test
    public void whenProductsSimilarById_OK() {
        List<String> result = Arrays.asList("PRODUCT-001", "PRODUCT-002");
        Mockito.when(productService.getProductSimilar(Mockito.anyString())).thenReturn(result);
        ResponseEntity<List<String>> response = productController.getProductSimilar(UUID.randomUUID().toString());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().size(), 2);
        Assertions.assertEquals(response.getBody().get(0), result.get(0));
    }


    private ProductDTO initProduct() {
        return ProductDTO.builder()
                .id(UUID.randomUUID().toString())
                .name("Nombre del producto")
                .price(500.25)
                .availability(Boolean.TRUE)
                .build();
    }

}
