//package com.aop.cosmeticsonlinestore.aspects;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.ui.Model;
//
//@Aspect
//public aspect ProductAspect {
//
//    private static final Logger logger = LoggerFactory.getLogger(ProductAspect.class);
//
//    pointcut callViewProduct(Long id, Model model) :
//            execution(String com.aop.cosmeticsonlinestore.controller.ProductsController.getProductById(Long, Model)) && args(id, model);
//
//    before(Long id, Model model) : callViewProduct(id, model) {
//        logger.info("Product with id " + id + " is being viewed");
//    }
//}
