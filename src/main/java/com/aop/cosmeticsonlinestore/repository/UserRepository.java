package com.aop.cosmeticsonlinestore.repository;

import com.aop.cosmeticsonlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);

    @Override
    User save(User entity);
    @Override
    Optional<User> findById(Long aLong);
}
