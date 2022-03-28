package com.aop.cosmeticsonlinestore.aspects;

public aspect AuthAspect {
//    pointcut callAddToCart(Long productId) : call(* CartController.addToCart(..)) && args(productId);
    pointcut hasValidFields() : execution (* com.aop.cosmeticsonlinestore.model.request.AuthRequest.hasValidFields(..));

//    pointcut callHasValidFields1() : call (* AuthRequest.hasValidFields(..));

    before() : hasValidFields() {
        System.out.println("Call is enabled - test test test test test test");
    }

}
