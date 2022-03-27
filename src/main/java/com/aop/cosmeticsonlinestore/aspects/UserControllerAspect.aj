package com.aop.cosmeticsonlinestore.aspects;

import com.aop.cosmeticsonlinestore.model.User;
import com.aop.cosmeticsonlinestore.model.request.AuthRequest;
import com.aop.cosmeticsonlinestore.model.request.RegistrationRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public aspect UserControllerAspect {

        pointcut registration(RegistrationRequest registrationRequest, BindingResult bindingResult, Model model)
                : execution(String com.aop.cosmeticsonlinestore.controller.UserController.saveRegistration(RegistrationRequest, BindingResult, Model))
                && args(registrationRequest, bindingResult, model);

        before(RegistrationRequest registrationRequest, BindingResult bindingResult, Model model):
                registration(registrationRequest, bindingResult, model) {
                System.out.println("USer is trying to register with username :" + registrationRequest.getUsername());
        }

        after(RegistrationRequest registrationRequest, BindingResult bindingResult, Model model)
                returning(): registration(registrationRequest, bindingResult, model) {
                System.out.println("User registration : " + registrationRequest.getUsername());
        }

        //    pointcut loginMethod(): execution(Object com.aop.cosmeticsonlinestore.controller.UserController.saveLogin(..));

        pointcut loginMethod(AuthRequest request, BindingResult bindingResult, Model model):
                execution(Object com.aop.cosmeticsonlinestore.controller.UserController.saveLogin(AuthRequest, BindingResult, Model) throws Exception)
                        && args(request, bindingResult, model) ;

        before(AuthRequest request, BindingResult bindingResult, Model model):
                loginMethod(request, bindingResult, model) {
                System.out.println("Before user " + request.getUsername() + "tries to login ");
        }

        //se executa indiferent daca se arunca exceptie
        after(AuthRequest request, BindingResult bindingResult, Model model):
                loginMethod(request, bindingResult, model) {
                System.out.println("User login ended for username: " + request.getUsername());
        }

        //doar daca credentialele sunt valide
        after(AuthRequest request, BindingResult bindingResult, Model model) returning(): loginMethod(request, bindingResult, model) {
                System.out.println("User logged in with success for username: " + request.getUsername());
        }

        //doar daca credentialele nu sunt bune
        after(AuthRequest request, BindingResult bindingResult, Model model) throwing(): loginMethod(request, bindingResult, model) {
                System.out.println("User could not login with username : " + request.getUsername());
        }
}

