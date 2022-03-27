package com.aop.cosmeticsonlinestore.aspects;

import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect CartAspect {
    private static final Logger logger = LoggerFactory.getLogger(CartAspect.class);

    pointcut callAddToCart(Long productId) :
            execution(String com.aop.cosmeticsonlinestore.controller.CartController.addToCart(Long)) && args(productId);

    pointcut callSaveOrder() :
            execution(String com.aop.cosmeticsonlinestore.controller.CartController.saveOrder(..));

    after(Long productId) throwing (Exception exception): callAddToCart(productId) {
        Signature signature = thisJoinPoint.getSignature();
        logger.info(exception.getMessage());
        logger.info(signature.getName());
    }

    after(Long productId) : callAddToCart(productId) {
        logger.info("Product with id " + productId + " was added to cart");
    }

    before() : callSaveOrder() {
        logger.info("An order is about to be placed");
    }
}
