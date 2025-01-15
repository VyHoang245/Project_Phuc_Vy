package com.models;

import jakarta.persistence.*;

@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    
}
