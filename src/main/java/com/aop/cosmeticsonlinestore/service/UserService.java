package com.aop.cosmeticsonlinestore.service;

import com.aop.cosmeticsonlinestore.model.User;
import com.aop.cosmeticsonlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
