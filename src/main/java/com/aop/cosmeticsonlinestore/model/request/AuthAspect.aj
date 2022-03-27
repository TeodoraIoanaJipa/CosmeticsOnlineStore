package com.aop.cosmeticsonlinestore.model.request;

public aspect AuthAspect {
//    pointcut callAddToCart(Long productId) : call(* CartController.addToCart(..)) && args(productId);
    pointcut hasValidFields() : execution (* AuthRequest.hasValidFields(..));

    pointcut callHasValidFields1() : call (* AuthRequest.hasValidFields(..));

    before() : hasValidFields() {
        System.out.println("Call is enabled - test test test test test test");
    }
    before() : callHasValidFields1() {
        System.out.println("Call is enabled - test test test test test test");
    }

    after() returning() : hasValidFields() {
        System.out.println("dvfvfff");
    }

    before() : callHasValidFields1() {
        System.out.println("Call iscdfdf test test test");
    }

    after() : hasValidFields() {
        System.out.println("LALALALALAALAL");
    }
}
