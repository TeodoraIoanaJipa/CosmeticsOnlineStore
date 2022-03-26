package com.aop.cosmeticsonlinestore.repository;

import com.aop.cosmeticsonlinestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    <S extends Order> S save(S entity);
}
