package com.asia.Shop.controller;

import com.asia.Shop.dto.ErrorDto;
import com.asia.Shop.entity.CartProductEntity;
import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.exception.ProductServiceException;
import com.asia.Shop.service.CartProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartProductController
{
    private CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService)
    {
        this.cartProductService = cartProductService;
    }

    @PostMapping("/api/cartProduct")
    public ResponseEntity<ErrorDto> addProduct(@RequestBody CartProductEntity cartProductEntity)
    {
        try
        {
            cartProductService.addCartProduct(cartProductEntity);
            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (ProductServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(400));
        }

    }

    //jaki tu ma byc mapping, chyba powinien byc z ID? bo w ten sposob to jest wyciaganie wszystkich ze wszyystkich carts, troche bez sensu?
    @GetMapping("/api/cartProducts")
    public ResponseEntity<List<CartProductEntity>> getProducts()
    {
        List<CartProductEntity> products = cartProductService.getCartProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/api/cartProducts/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        try
        {
            cartProductService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted");
        }
        catch(ProductServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(404));
        }

    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody CartProductEntity cartProductEntity)
    {
        cartProductEntity.setId(id);
        try
        {
            cartProductService.updateCartProduct(cartProductEntity);
            return ResponseEntity.ok("CartProduct updated");
        }
        catch (ProductServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(400));
        }


    }

}
