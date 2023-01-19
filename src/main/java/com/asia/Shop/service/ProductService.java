package com.asia.Shop.service;

import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.exception.ProductServiceException;
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
        if(product.getPrice() == null)
        {
            throw new ProductServiceException("No price specified");
        }
        productRepository.save(product);
    }

    public List<ProductEntity> getProducts()
    {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id)
    {
        ProductEntity productEntity = productRepository.findById(id).get();
        productRepository.delete(productEntity);
    }

    public void updateProduct(ProductEntity productEntity)
    {
        productRepository.findById(productEntity.getId()).orElseThrow();

        productRepository.save(productEntity);
    }

}
