package com.asia.Shop.service;

import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductEntity product)
    {
        productRepository.save(product);
    }

    public List<ProductEntity> getProducts()
    {
        return productRepository.findAll();
    }
}
