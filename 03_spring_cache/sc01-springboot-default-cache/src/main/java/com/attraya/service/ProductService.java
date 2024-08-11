package com.attraya.service;

import com.attraya.entity.Product;
import com.attraya.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

//    @PostConstruct
//    public void initDB(){
//        List<Product> products = IntStream.rangeClosed(1, 10000).mapToObj(i -> new Product("product" + i, new Random().nextInt(5000), "description" + i, "type" + i)).collect(Collectors.toList());
//        productRepository.saveAll(products);
//    }

    @CachePut(cacheNames = "products", key = "#product.id") // annotation to make sure if any new product added to DB, it should be added to cache as well
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Cacheable(cacheNames = "products")
    public List<Product> getProducts(){
        log.info("ProductService::productService() connecting to Database...");
        return productRepository.findAll();
    }

    @Cacheable(cacheNames = "products", key = "#id")
    public Product getProductById(int id){
        log.info("ProductService::getProductById() connecting to Database...");
        return productRepository.findById(id).get();
    }

    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> getProductsByType(String type){
        return productRepository.findByProductType(type);
    }

    public List<Product> getProductWithPriceAndType(double price, String productType){
        return productRepository.findByPriceAndProductType(price, productType);
    }

    public List<Product> getProductsByPrice(double price){
        return productRepository.getProductByPrice(price);
    }

    @CachePut(cacheNames = "products", key = "#id") // annotation to make sure if any new product added to DB, it should be added to cache as well
    public Product updateProduct(int id, Product productRequest){
        // get the product from DB by id
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(productRequest.getProductType());

        // update with new value getting from request
        return productRepository.save(existingProduct);
    }

    @CacheEvict(cacheNames = "products", key = "#id") // annotation to make sure it gets deleted from cache as well
    public long deleteProduct(int id){
        productRepository.deleteById(id);
        return productRepository.count();
    }
}
