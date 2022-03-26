package com.aop.cosmeticsonlinestore.repository;

import com.aop.cosmeticsonlinestore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Page<Product> findAll(Pageable pageable);

    @Override
    Optional<Product> findById(Long aLong);
}
