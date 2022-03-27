package com.aop.cosmeticsonlinestore.aspects;

import com.aop.cosmeticsonlinestore.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public aspect UserAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);

    pointcut callAddToCart(Long productId) :
        execution(String com.aop.cosmeticsonlinestore.controller.CartController.addToCart(Long)) && args(productId);
    pointcut callUserIsValid(String userName, User user):
        call(Boolean com.aop.cosmeticsonlinestore.model.User.isValid(java.lang.String)) && args(userName) && target(user);


//    pointcut callFinalizeOrder() : call(* CartController.finalizeOrder(..));
//
//    before(String userName, User user): callUserIsValid(userName, user) {
//        System.out.println("test test test test test test");
//        logger.info("test");
//    }
//
//    before(Long productId): callAddToCart(productId) {
//        logger.info("test controller");
//        System.out.println("test controller");
//    }
//    before() : callFinalizeOrder() {
//        System.out.println("test test test test test test");
//    }

//    boolean around(int amount, Account acc) :
//            callWithDraw(amount, acc) {
//        if (acc.balance < amount) {
//            return false;
//        }
//        return proceed(amount, acc);
//    }
//
//    after(int amount, Account balance) : callWithDraw(amount, balance) {
//    }
}