package com.soto.ecommerce.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents an order in an e-commerce system.
 */

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


    public Order() {
    }

    public Order(Integer id, String number, Date creationDate, Date receivedDate, User user, OrderDetails orderDetails) {
        this.id = id;
        this.number = number;
        this.creationDate = creationDate;
        this.receivedDate = receivedDate;
        this.user = user;
        this.orderDetails = orderDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate=" + creationDate +
                ", receivedDate=" + receivedDate +
                ", user=" + user.getId() +
                ", orderDetails=" + orderDetails.getId() +
                '}';
    }
}
