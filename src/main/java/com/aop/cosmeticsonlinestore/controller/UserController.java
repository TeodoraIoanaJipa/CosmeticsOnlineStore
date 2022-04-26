package com.aop.cosmeticsonlinestore.controller;

import com.aop.cosmeticsonlinestore.config.security.JwtTokenUtil;
import com.aop.cosmeticsonlinestore.model.request.AuthRequest;
import com.aop.cosmeticsonlinestore.model.request.RegistrationRequest;
import com.aop.cosmeticsonlinestore.model.User;
import com.aop.cosmeticsonlinestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String registration(Model model) {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        model.addAttribute("registrationRequest", registrationRequest);
        return "home/registration";
    }

    @PostMapping("/signup")
    public String saveRegistration(
            @Valid @ModelAttribute("registrationRequest") RegistrationRequest registrationRequest,
            BindingResult bindingResult,
            Model model
    ) {

        boolean isConfirmEmpty = StringUtils.isEmpty(registrationRequest.getRePassword());
        boolean isPasswordDifferent = registrationRequest.getPassword() != null &&
                !registrationRequest.getPassword().equals(registrationRequest.getRePassword());

        if (isConfirmEmpty || isPasswordDifferent) {
            model.addAttribute("password2Error", "Cele 2 parole trebuie sa fie identice.");
        }

        if (isConfirmEmpty || isPasswordDifferent || bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "home/registration";
        }

        try {
            User user = userService.convertFromRegistrationRequest(registrationRequest);
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user = userService.save(user);

            return "home/user/login";
        }catch(Exception exception) {
            return "home/registration";
        }
    }

    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    @GetMapping("/login")
    public String login(Model model) {
        AuthRequest request = new AuthRequest();
        request.hasValidFields();
        model.addAttribute("request", request);
        return "home/user/login";
    }

    @PostMapping("/login")
    public Object saveLogin(@ModelAttribute("request") @Valid AuthRequest request,
                            BindingResult bindingResult,
                            Model model) throws Exception {

        try {
            Authentication authenticate = userService.login(authenticationManager, request);

            User user = (User) authenticate.getPrincipal();

            userService.generateToken(user);

            return "redirect:/";
        } catch (Exception exception) {
            return "home/user/login";
        }
    }

    @PostMapping("/logout")
    public String logout(){
        this.userService.removeToken();

        return "redirect:/user/login";
    }

}
