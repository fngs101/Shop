package com.asia.Shop.service;

import com.asia.Shop.entity.CartProductEntity;
import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.exception.ProductServiceException;
import com.asia.Shop.repository.CartProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService
{
    private CartProductRepository cartProductRepository;

    public CartProductService(CartProductRepository cartProductRepository)
    {
        this.cartProductRepository = cartProductRepository;
    }

    public void addCartProduct(CartProductEntity cartProductEntity)
    {
        if(cartProductEntity.getProductEntity() == null)
        {
            throw new ProductServiceException("No product specified");
        }
        if(cartProductEntity.getAmount() == null)
        {
            throw new ProductServiceException("No amount of product specified");
        }
        cartProductRepository.save(cartProductEntity);
    }

    public List<CartProductEntity> getCartProducts()
    {
        return cartProductRepository.findAll();
    }

    public void deleteProduct(Long id)
    {
        CartProductEntity cartProductEntity = cartProductRepository.findById(id).orElseThrow(() -> new ProductServiceException("There is no product with such ID"));
        cartProductRepository.delete(cartProductEntity);
    }

    public void updateCartProduct(CartProductEntity cartProductEntity)
    {
        cartProductRepository.findById(cartProductEntity.getId()).orElseThrow(() -> new ProductServiceException("There is no product with such ID"));

        cartProductRepository.save(cartProductEntity);
    }
}
