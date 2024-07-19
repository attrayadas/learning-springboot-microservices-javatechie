package com.attraya.controller;

import com.attraya.entity.Product;
import com.attraya.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    /* Operator */

    @PostMapping("/search")
    public List<Product> getProductsByMultiplePriceValue(@RequestBody List<Double> prices){
        return productService.getProductsByMultiplePriceValue(prices);
    }

    @GetMapping("/min/{minPrice}/max/{maxPrice}")
    public List<Product> getProductsWithinPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice){
        return productService.getProductsWithinPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/high/{price}")
    public List<Product> getProductsWithHigherPrice(@PathVariable double price){
        return productService.getProductsWithHigherPrice(price);
    }

    @GetMapping("/less/{price}")
    public List<Product> getProductsWithLowerPrice(@PathVariable double price){
        return productService.getProductsWithLowerPrice(price);
    }

    @GetMapping("/like/{name}")
    public List<Product> getProductsWithLike(@PathVariable String name){
        return productService.getProductsWithLike(name);
    }

    /* Sorting */
    @GetMapping("/sort/{fieldName}")
    public List<Product> getProductsWithSorting(@PathVariable String fieldName){
        return productService.getProductsWithSorting(fieldName);
    }

    /* Pagination */
    @GetMapping("/page/{offset}/{limit}")
    public Page<Product> getProductWithPageResponse(@PathVariable int offset, @PathVariable int limit){
        return productService.getProductWithPageResponse(offset, limit);
    }

    /* Sorting & Pagination */
    @GetMapping("/pageWithSort/{fieldName}/{offset}/{limit}")
    public Page<Product> getProductsWithSortingAndPagination(@PathVariable String fieldName, @PathVariable int offset, @PathVariable int limit){
        return productService.getProductsWithSortingAndPagination(fieldName, offset, limit);
    }
}
