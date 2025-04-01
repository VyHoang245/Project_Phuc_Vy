package com.project.vpweb.services;

import com.project.vpweb.models.OrderDetail;
import com.project.vpweb.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() {return orderDetailRepository.findAll();}
}
