package com.aop.cosmeticsonlinestore.repository;

import com.aop.cosmeticsonlinestore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Long> {
    @Override
    <S extends OrderItem> S save(S entity);
}
