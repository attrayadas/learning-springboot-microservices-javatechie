package com.attraya.repository;

import com.attraya.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer > {

    /* prefix + field + operator */

    List<Product> findByPriceIn(List<Double> prices);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByNameIgnoreCaseContaining(String name);
}
