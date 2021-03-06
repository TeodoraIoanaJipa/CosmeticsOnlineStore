package com.aop.cosmeticsonlinestore.service;

import com.aop.cosmeticsonlinestore.model.request.RegistrationRequest;
import com.aop.cosmeticsonlinestore.model.User;
import com.aop.cosmeticsonlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        user.setActive(true);
        return userRepository.save(user);
    }

    public User convertFromRegistrationRequest(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setUsername(registrationRequest.getUsername());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        return user;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}