package com.asia.Shop.service;

import com.asia.Shop.entity.CartEntity;
import com.asia.Shop.entity.CartProductEntity;
import com.asia.Shop.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService
{
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository)
    {
        this.cartRepository = cartRepository;
    }


    public List<CartProductEntity> getProductsInCart(Long cartId)
    {
       CartEntity cart = cartRepository.findById(cartId).get();
       return cart.getProducts();
    }
}
