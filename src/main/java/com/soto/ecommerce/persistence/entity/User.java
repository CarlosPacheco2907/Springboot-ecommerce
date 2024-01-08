package com.soto.ecommerce.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Represents a user in the system.
 */
@Data
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<Product> products;
    @OneToMany(mappedBy = "user")
    private  List<Order> orders;


}
