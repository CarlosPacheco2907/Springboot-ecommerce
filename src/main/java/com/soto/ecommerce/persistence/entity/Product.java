package com.soto.ecommerce.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Represents a product in the e-commerce system.
 */
@Data
@Entity
@Table(name = "products")
public class Product {
    /**
     * Unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int quantity;
    @ManyToOne
    private User user;

}
