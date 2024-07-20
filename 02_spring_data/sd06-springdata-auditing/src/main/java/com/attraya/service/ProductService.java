package com.attraya.service;

import com.attraya.entity.Product;
import com.attraya.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(int id){
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

    public long deleteProduct(int id){
        productRepository.deleteById(id);
        return productRepository.count();
    }
}
