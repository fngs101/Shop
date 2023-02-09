package com.asia.Shop.controller;

import com.asia.Shop.entity.CartEntity;
import com.asia.Shop.entity.CartProductEntity;
import com.asia.Shop.exception.CartServiceException;
import com.asia.Shop.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CartEntity>> getCarts()
    {
        List<CartEntity> carts =  cartService.getCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("api/carts/{id}")
    public ResponseEntity<CartEntity> getCartById(@PathVariable Long id)
    {
        try
        {
            CartEntity cart = cartService.getCartById(id);
            return new ResponseEntity<>(cart, HttpStatus.valueOf(200));
        }
        catch(CartServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }

    }

    @GetMapping("api/carts/{id}/products")
    public ResponseEntity<List<CartProductEntity>> getListOfProductsInCart(@PathVariable Long id)
    {
        try
        {
            List<CartProductEntity> productsInCart = cartService.getProductsInCart(id);
            return new ResponseEntity<>(productsInCart, HttpStatus.valueOf(200));
        }
        catch(CartServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
    }

    @PostMapping("/api/carts")
    public void addCart(@RequestBody CartEntity cartEntity)
    {
        cartService.addCart(cartEntity);
    }
}
