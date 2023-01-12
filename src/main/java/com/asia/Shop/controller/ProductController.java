package com.asia.Shop.controller;

import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController
{
    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping("api/products")
    public void addProduct(@RequestBody ProductEntity productEntity)
    {
        productService.addProduct(productEntity);
    }

    @GetMapping("api/products")
    public List<ProductEntity> getProducts()
    {
        return productService.getProducts();
    }

    @DeleteMapping("api/products/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

    @PutMapping("api/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductEntity productEntity)
    {
        productEntity.setId(id);
        try
        {
            productService.updateProduct(productEntity);
        }
        catch(NoSuchElementException e)
        {
           return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }

}
