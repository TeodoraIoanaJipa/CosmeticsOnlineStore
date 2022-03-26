package com.aop.cosmeticsonlinestore.service;

import com.aop.cosmeticsonlinestore.model.Order;
import com.aop.cosmeticsonlinestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
