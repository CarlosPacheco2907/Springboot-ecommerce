package com.soto.ecommerce.persistence.repository;

import com.soto.ecommerce.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {




}
