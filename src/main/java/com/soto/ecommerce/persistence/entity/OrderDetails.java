package com.soto.ecommerce.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents the details of an order in an e-commerce system.
 */
@Data
@Entity
@Table(name = "order_details")
public class OrderDetails {
    /**
     * Unique identifier of the order detail.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int quantity;
    private double price;
    private double total;

    @OneToOne
    private Order order;
    @ManyToOne
    private Product product;

}
