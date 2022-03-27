package com.aop.cosmeticsonlinestore.aspects;

import com.aop.cosmeticsonlinestore.model.OrderItem;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect CartAspect {
    private static final Logger logger = LoggerFactory.getLogger(CartAspect.class);

    pointcut callAddToCart(Long productId) :
            execution(String com.aop.cosmeticsonlinestore.controller.CartController.addToCart(Long)) && args(productId);

    pointcut callRemoveFromCart(Long productId) :
            execution(String com.aop.cosmeticsonlinestore.controller.CartController.removeItemFromCart(Long)) && args(productId);

    pointcut callSaveOrder() :
            execution(String com.aop.cosmeticsonlinestore.controller.CartController.saveOrder(..));

    pointcut callSaveOrderItem(OrderItem orderItem) :
            call(OrderItem com.aop.cosmeticsonlinestore.service.OrderItemService.save(..)) && args(orderItem);

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

    after() : callSaveOrder() {
        logger.info("An order has been saved");
    }

    String around(Long productId) : callRemoveFromCart(productId) {
        logger.info("Product with id " + productId + " is being removed from cart");
        return proceed(productId);
    }

    after(OrderItem orderItem) : callSaveOrderItem(orderItem) {
        logger.info("Product " + orderItem.getProduct().getTitle() + " was added to the order");
    }
}
