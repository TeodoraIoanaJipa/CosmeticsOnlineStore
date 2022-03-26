package com.aop.cosmeticsonlinestore.controller;

import com.aop.cosmeticsonlinestore.config.security.JwtTokenUtil;
import com.aop.cosmeticsonlinestore.model.request.AuthRequest;
import com.aop.cosmeticsonlinestore.model.request.RegistrationRequest;
import com.aop.cosmeticsonlinestore.model.User;
import com.aop.cosmeticsonlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String registration(Model model) {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        model.addAttribute("registrationRequest", registrationRequest);
        return "home/registration";
    }

    @PostMapping("/signup")
    public String registration(
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

        User user = userService.convertFromRegistrationRequest(registrationRequest);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user = userService.save(user);

        logger.debug("Registration with success", user.getUsername());

        return "redirect:/login";
    }

    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody @Valid AuthRequest request) {
//        try {
//            Authentication authenticate = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//
//            User user = (User) authenticate.getPrincipal();
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
//                    .body(userViewMapper.toUserView(user));
//        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
    }
}
