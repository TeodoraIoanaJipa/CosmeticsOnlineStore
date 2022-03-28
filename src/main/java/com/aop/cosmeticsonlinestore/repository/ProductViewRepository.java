package com.aop.cosmeticsonlinestore.repository;

import com.aop.cosmeticsonlinestore.model.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Optional;

public interface ProductViewRepository extends JpaRepository<ProductView, Long> {

    @Override
    <S extends ProductView> S save(S entity);

    @Override
    Optional<ProductView> findById(Long aLong);

    @Query("SELECT pw FROM ProductView pw WHERE pw.product=:#{#id}")
    Collection<ProductView> findByProductId(@Param("id") Long id);
}
