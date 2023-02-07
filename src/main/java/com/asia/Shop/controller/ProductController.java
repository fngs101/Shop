package com.asia.Shop.controller;

import com.asia.Shop.dto.ErrorDto;
import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.exception.ProductServiceException;
import com.asia.Shop.service.ProductService;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/api/products")
    public ResponseEntity<ErrorDto> addProduct(@RequestBody ProductEntity productEntity)
    {
        try
        {
            productService.addProduct(productEntity);
            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (ProductServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(400));
        }

    }

    //opcja nizej pozwala na wszystko, body, status, headers?
    @GetMapping("/api/products")
    public ResponseEntity<List<ProductEntity>> getProducts()
    {
        List<ProductEntity> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //opcja nizej tylko dla prostych gettow, malo flexibility?
//    @GetMapping("/api/products")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ProductEntity> getProductsResponseStatusVersion()
//    {
//        List<ProductEntity> products = productService.getProducts();
//        return products;
//    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        try
        {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted");
        }
        catch(ProductServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(404));
        }

    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductEntity productEntity)
    {
        productEntity.setId(id);
        try
        {
            productService.updateProduct(productEntity);
        }
        catch (ProductServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(404));
        }

        return ResponseEntity.ok("Product updated");
    }

}
