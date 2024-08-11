package com.attraya.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
    private String productType;

    public Product(String name, double price, String description, String productType) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }
}
