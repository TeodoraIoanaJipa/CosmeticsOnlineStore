package com.aop.cosmeticsonlinestore.service;

import com.aop.cosmeticsonlinestore.model.OrderItem;
import com.aop.cosmeticsonlinestore.repository.OrderItemRepository;
import com.aop.cosmeticsonlinestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
