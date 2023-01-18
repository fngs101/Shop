package com.asia.Shop.controller;

import com.asia.Shop.entity.CartEntity;
import com.asia.Shop.entity.CartProductEntity;
import com.asia.Shop.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController
{
    private CartService cartService;

    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }

    @GetMapping("api/carts")
    public List<CartEntity> getCarts()
    {
        return cartService.getCarts();
    }

    @GetMapping("api/carts/{id}")
    public CartEntity getCartById(@PathVariable Long id)
    {
        return cartService.getCartById(id);
    }

    @GetMapping("api/carts/{id}/products")
    public List<CartProductEntity> getListOfProductsInCart(@PathVariable Long id)
    {
        return cartService.getProductsInCart(id);
    }
}
