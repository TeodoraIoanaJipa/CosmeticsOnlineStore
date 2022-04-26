package com.aop.cosmeticsonlinestore.service;

import com.aop.cosmeticsonlinestore.config.security.JwtTokenUtil;
import com.aop.cosmeticsonlinestore.model.request.AuthRequest;
import com.aop.cosmeticsonlinestore.model.request.RegistrationRequest;
import com.aop.cosmeticsonlinestore.model.User;
import com.aop.cosmeticsonlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private String token;
    private String username;

    public UserService(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User save(User user) {
        user.setActive(true);
        return userRepository.save(user);
    }

    public Authentication login(AuthenticationManager authenticationManager, AuthRequest request) throws Exception {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return authenticate;
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

    public void removeToken() {
        token = null;
        username = null;
    }

    public void generateToken(User user) {
        if (user != null) {
            token = jwtTokenUtil.generateAccessToken(user);
            username = user.getUsername();
        }
    }

    public boolean isUserLogged() {
        if (token == null) {
            return false;
        }
        return (jwtTokenUtil.validate(token));
    }

    public String getToken() {
        return token;
    }
}