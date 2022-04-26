package com.aop.cosmeticsonlinestore.aspects;

import com.aop.cosmeticsonlinestore.model.request.AuthRequest;
import com.aop.cosmeticsonlinestore.model.request.RegistrationRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class UserControllerAspectV2 {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerAspectV2.class);

    @Pointcut("execution(* saveRegistration(..))")
    public void registration() {
    }


    @Before("registration()")
    public void beforeRegistration(JoinPoint jp) {
        RegistrationRequest registrationRequest = (RegistrationRequest) jp.getArgs()[0];
        logger.info("User is trying to register with username :" + registrationRequest.getUsername());
    }

    @Pointcut("execution(Object saveLogin(..))")
    public void login() {
    }


    @After("login()")
    public void myadvice(JoinPoint jp) {
        AuthRequest authRequest = (AuthRequest) jp.getArgs()[0];
        logger.info("Trying login for username " + authRequest.getUsername());
    }
}
