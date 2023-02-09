package com.asia.Shop.service;

import com.asia.Shop.entity.CartEntity;
import com.asia.Shop.entity.CartProductEntity;
import com.asia.Shop.exception.CartServiceException;
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

    public List<CartEntity> getCarts()
    {
        return cartRepository.findAll();
    }

    public CartEntity getCartById(Long id)
    {
        return cartRepository.findById(id).orElseThrow(() -> new CartServiceException("No cart with such ID"));
    }
    public List<CartProductEntity> getProductsInCart(Long cartId)
    {
       CartEntity cart = cartRepository.findById(cartId).orElseThrow(() -> new CartServiceException("No cart with such ID"));
       return cart.getCartProducts();
    }

    public void addCart(CartEntity cartEntity)
    {
        cartRepository.save(cartEntity);
    }
}
