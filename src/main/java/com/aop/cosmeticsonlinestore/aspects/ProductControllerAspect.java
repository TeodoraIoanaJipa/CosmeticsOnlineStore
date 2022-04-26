package com.aop.cosmeticsonlinestore.aspects;

import com.aop.cosmeticsonlinestore.model.Product;
import com.aop.cosmeticsonlinestore.model.ProductView;
import com.aop.cosmeticsonlinestore.service.ProductService;
import com.aop.cosmeticsonlinestore.service.ProductViewService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;


@Aspect
public class ProductControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(ProductControllerAspect.class);

    @Pointcut("execution(* com.aop.cosmeticsonlinestore.controller.ProductsController.getProductById(..)) && args(id, model)")
    private void callViewProduct(Long id, Model model) {}

    @After("callViewProduct(id, model)")
    public void afterCallViewProduct(Long id, Model model) {
        logger.info("Product with id " + id + " is being viewed");
    }
}
