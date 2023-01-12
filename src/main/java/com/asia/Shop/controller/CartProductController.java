package com.asia.Shop.controller;

import com.asia.Shop.service.CartProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartProductController
{
    private CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService)
    {
        this.cartProductService = cartProductService;
    }
}
