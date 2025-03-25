package com.project.vpweb.services;

import com.project.vpweb.models.Order;
import com.project.vpweb.models.OrderDetail;
import com.project.vpweb.repository.OrderDetailRepository;
import com.project.vpweb.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public Order saveOrder(Order order, List<OrderDetail> orderDetails) {
        order.setOrderDetails(orderDetails);
        return orderRepository.save(order);
    }
}

