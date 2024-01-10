package com.soto.ecommerce.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents the details of an order in an e-commerce system.
 */

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

    public OrderDetails() {
    }

    public OrderDetails(Integer id, String name, int quantity, double price, double total, Order order, Product product) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.order = order;
        this.product = product;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
