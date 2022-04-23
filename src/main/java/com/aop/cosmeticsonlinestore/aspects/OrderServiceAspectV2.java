package com.aop.cosmeticsonlinestore.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class OrderServiceAspectV2 {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceAspectV2.class);

    @AfterReturning(
            pointcut = "execution(* com.aop.cosmeticsonlinestore.service.OrderService.*(..))", returning = "result")
    public void saveOrder(JoinPoint jp, Object result) {
        logger.info("Order - Order saved to database : " + result);
    }
}
