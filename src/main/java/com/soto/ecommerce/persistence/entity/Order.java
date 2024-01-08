package com.soto.ecommerce.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * Represents an order in an e-commerce system.
 */
@Data
@Entity
@Table(name = "orders")
public class Order {
    /**
     * Unique identifier of the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private Date creationDate;
    private Date receivedDate;

    @ManyToOne
    private User user;
    @OneToOne(mappedBy = "order")
    private OrderDetails orderDetails;

}
