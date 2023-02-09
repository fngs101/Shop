package com.asia.Shop.service;

import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.exception.ProductServiceException;
import com.asia.Shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest
{

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveProduct()
    {
        ProductEntity productEntity = new ProductEntity(1L, "apple", 10);

        productService.addProduct(productEntity);

        Mockito.verify(productRepository)
                .save(productEntity);

    }

    @Test
    void shouldFailWhenPriceNull()
    {
        ProductEntity productEntity = new ProductEntity(1L, "apple", null);
            //powtorzyc lambdy
        assertThrows(ProductServiceException.class, () -> productService.addProduct(productEntity));

    }

    @Test
    void getProducts()
    {
        ProductEntity productEntity = new ProductEntity(1L, "apple", 10);
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);

        Mockito.when(productRepository.findAll()).thenReturn(productEntityList);

        List<ProductEntity> productEntities = productService.getProducts();
        assertEquals(productEntities, productEntityList);


    }

    @Test
    void deleteProduct()
    {
        ProductEntity productEntity = new ProductEntity(1L, "banana", 10);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));

        productService.deleteProduct(1L);

        Mockito.verify(productRepository)
                .delete(productEntity);
    }

    //wersja z bledem do testu do powzyszego

    @Test
    void updateProduct()
    {
        ProductEntity productEntity = new ProductEntity(1L, "banana", 10);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        ProductEntity productUpdate = new ProductEntity(1L,"apple", 10);

        productService.updateProduct(productUpdate);

        Mockito.verify(productRepository)
                .save(productUpdate);
    }

    //wersja z bledem do testu do powzyszego
}