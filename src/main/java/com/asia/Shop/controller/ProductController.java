package com.asia.Shop.controller;

import com.asia.Shop.service.ProductService;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController
{
    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
}
