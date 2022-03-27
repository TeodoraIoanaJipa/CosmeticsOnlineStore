package com.aop.cosmeticsonlinestore.aspects;

public aspect CartAspect {
//    pointcut callAddToCart(Long productId) : call(* CartController.addToCart(..)) && args(productId);
    pointcut callFinalizeOrder() : call(* CartController.finalizeOrder(..));

//    before(Long productId) : callAddToCart(productId) {
//        System.out.println(productId);
//    }
    before() : callFinalizeOrder() {
        System.out.println("test test test test test test");
    }

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