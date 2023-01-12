package com.asia.Shop.controller;

import com.asia.Shop.service.CartService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController
{
    private CartService cartService;

    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }
}
