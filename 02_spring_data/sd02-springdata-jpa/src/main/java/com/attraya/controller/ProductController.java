package com.attraya.controller;

import com.attraya.entity.Product;
import com.attraya.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

    @GetMapping("/type/{productType}")
    public List<Product> getProductsByType(@PathVariable String productType){
        return productService.getProductsByType(productType);
    }

    @GetMapping("/price/{price}/type/{productType}")
    public List<Product> getProductWithPriceAndType(@PathVariable double price, @PathVariable String productType){
        return productService.getProductWithPriceAndType(price, productType);
    }

    @GetMapping("/search/{price}")
    public List<Product> getProductsByPrice(@PathVariable double price){
        return productService.getProductsByPrice(price);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest){
        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public long deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
}
