package com.aop.cosmeticsonlinestore.controller;

import com.aop.cosmeticsonlinestore.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/signup")
    public String registration() {
        return "home/registration";
    }

    @PostMapping("/signup")
    public String registration(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
//        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
//
//        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
//
//        if (!response.isSuccess()) {
//            model.addAttribute("captchaError", "Fill captcha");
//        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean isPasswordDifferent = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Подтверждение пароля не может быть пустым");
        }

//        if (isPasswordDifferent) {
//            model.addAttribute("passwordError", "Пароли не совпадают");
//        }
//
//        if (isConfirmEmpty || isPasswordDifferent || bindingResult.hasErrors() || !response.isSuccess()) {
//            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
//
//            model.mergeAttributes(errors);
//
//            return "registration";
//        }
//
//        if (!userService.addUser(user)) {
//            model.addAttribute("usernameError", "Пользователь существует!");
//            return "registration";
//        }

        logger.debug("Registration with success", user.getUsername());

        return "redirect:/login";
    }
}
