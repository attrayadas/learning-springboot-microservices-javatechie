package com.attraya.repository;

import com.attraya.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends RevisionRepository<Product, Integer, Integer>,
        JpaRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByProductType(String type);

    List<Product> findByPriceAndProductType(double price, String productType);

    @Query("from Product p where p.price = ?1") // position based parameter
    List<Product> getProductByPrice(@Param("price") double price);
}
