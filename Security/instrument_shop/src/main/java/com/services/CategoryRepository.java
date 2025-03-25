package com.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}