package com.soto.ecommerce.service;

import com.soto.ecommerce.persistence.entity.Product;

import java.util.Optional;

public interface ProductService {

    public Product saveProduct(Product product);
    public Optional<Product> getProduct(Integer id);
    public void updateProduct(Product product);
    public void deleteProduct(Integer id);

}
