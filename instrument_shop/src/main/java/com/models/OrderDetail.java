package com.models;

import jakarta.persistence.*;

@Entity
@Table(name = "orderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    private int quantity;
}
