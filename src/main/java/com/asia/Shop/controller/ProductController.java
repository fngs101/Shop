package com.asia.Shop.controller;

import com.asia.Shop.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController
{
    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
}
