package com.asia.Shop.service;

import com.asia.Shop.entity.CartEntity;
import com.asia.Shop.entity.OrderEntity;
import com.asia.Shop.entity.ProductEntity;
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


    public List<ProductEntity> getProductsInCart(Long cartId)
    {
       CartEntity cart = cartRepository.findById(cartId).get();
       return cart.getProducts();
    }
}
