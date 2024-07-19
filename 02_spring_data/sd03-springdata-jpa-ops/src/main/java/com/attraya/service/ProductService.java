package com.attraya.service;

import com.attraya.entity.Product;
import com.attraya.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    /* Operator */

    public List<Product> getProductsByMultiplePriceValue(List<Double> prices){
        return productRepository.findByPriceIn(prices);
    }

    public List<Product> getProductsWithinPriceRange(double minPrice, double maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsWithHigherPrice(double price){
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> getProductsWithLowerPrice(double price){
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsWithLike(String name){
        return productRepository.findByNameIgnoreCaseContaining(name);
    }

    /* Sorting */

    public List<Product> getProductsWithSorting(String fieldName){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }

    /* Pagination */

    public Page<Product> getProductWithPageResponse(int offset, int limit){
        return productRepository.findAll(PageRequest.of(offset, limit));
    }

    /* Sorting & Pagination */

    public Page<Product> getProductsWithSortingAndPagination(String fieldName, int offset, int limit){
        return productRepository.findAll(PageRequest.of(offset, limit).withSort(Sort.by(fieldName)));
    }
}
