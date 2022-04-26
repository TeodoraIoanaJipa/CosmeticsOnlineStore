package com.aop.cosmeticsonlinestore.aspects;

import com.aop.cosmeticsonlinestore.model.OrderItem;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class CartControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(CartControllerAspect.class);

    @Pointcut("execution(* com.aop.cosmeticsonlinestore.controller.CartController.addToCart(Long)) && args(productId)")
    private void callAddToCart(Long productId) {}

    @After("callAddToCart(productId)")
    public void afterCallAddToCart(Long productId) {
        logger.info("Product with id " + productId + " was added to cart");
    }

    @Pointcut("execution(* com.aop.cosmeticsonlinestore.controller.CartController.removeItemFromCart(Long)) && args(productId)")
    private void callRemoveFromCart(Long productId) {}

    @Around("callRemoveFromCart(productId)")
    public String aroundCallRemoveFromCart(ProceedingJoinPoint proceedingJoinPoint, Long productId) throws Throwable {
        logger.info("Product with id " + productId + " is being removed from cart");
        Object[] args = proceedingJoinPoint.getArgs();
        String result = (String) proceedingJoinPoint.proceed(args);
        logger.info("Returning " + result);
        return result;
    }

    @Pointcut("execution(* com.aop.cosmeticsonlinestore.controller.CartController.saveOrder(..))")
    private void callSaveOrder() {}

    @After("callSaveOrder()")
    public void afterCallSaveOrder() {
        logger.info("An order has been saved");
    }

    @Pointcut("target(com.aop.cosmeticsonlinestore.service.OrderItemService)")
    private void callOrderItemService() {}

    @AfterReturning(value = "callOrderItemService()", returning = "returnValue")
    public void afterCallOrderItemService(JoinPoint joinPoint, Object returnValue) {
        logger.info(returnValue.toString());
    }
}
