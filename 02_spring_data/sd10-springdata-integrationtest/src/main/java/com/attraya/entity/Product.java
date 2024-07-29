package com.attraya.entity;


import lombok.*;

import javax.persistence.*;

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

    public Product(double price, String description, String productType, String name) {
        this.price = price;
        this.description = description;
        this.productType = productType;
        this.name = name;
    }
}
