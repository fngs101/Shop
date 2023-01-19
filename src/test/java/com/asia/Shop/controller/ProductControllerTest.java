package com.asia.Shop.controller;

import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest
{
    @Mock
    ProductService productService;
    @InjectMocks
    ProductController productController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @Test
    void addProduct() throws Exception
    {
        //ten nie dziala, add product powinien zwracac product zeby to dzialalo?
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Apple");
        productEntity.setPrice(10);

        mockMvc.perform(post("/api/products")
                        .content(asJsonString(productEntity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        verify(productService).addProduct(productEntity);

    }

    @Test
    void getProducts() throws Exception
    {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Banana");
        productEntity.setPrice(10);

        List<ProductEntity> products = Arrays.asList(productEntity);
        when(productService.getProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void deleteProduct() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateProduct() throws Exception
    {
        //ten nie dziala, add product powinien zwracac product zeby to dzialalo?
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Banana");

        ProductEntity productEntityUpdated = new ProductEntity();
        productEntityUpdated.setName("Banana");
        productEntityUpdated.setPrice(10);

//        when(productService.updateProduct(any(ProductEntity.class))).thenReturn(products);

        mockMvc.perform(get("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    public static String asJsonString(Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}