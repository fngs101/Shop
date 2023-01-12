package com.asia.Shop.service;

import com.asia.Shop.repository.CartProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartProductService
{
    private CartProductRepository cartProductRepository;

    public CartProductService(CartProductRepository cartProductRepository)
    {
        this.cartProductRepository = cartProductRepository;
    }
}
