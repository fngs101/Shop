package com.asia.Shop.controller;

import org.springframework.stereotype.Controller;

@Controller
public class ProductController
{
    private ProductController productController;

    public ProductController(ProductController productController)
    {
        this.productController = productController;
    }
}
