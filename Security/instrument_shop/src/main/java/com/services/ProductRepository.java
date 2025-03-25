package com.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
//16:57
}
