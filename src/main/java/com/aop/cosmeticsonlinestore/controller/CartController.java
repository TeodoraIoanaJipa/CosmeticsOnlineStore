package com.aop.cosmeticsonlinestore.controller;

import com.aop.cosmeticsonlinestore.model.Product;
import com.aop.cosmeticsonlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class CartController {
    private HashMap<Long, Product> cartProducts = new HashMap<>();
    private final ProductService productService;

    @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId) throws Exception {
        Optional<Product> optionalProduct = productService.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new Exception("Product not found");
        } else {
            Product product = optionalProduct.get();
            cartProducts.put(product.getId(), product);
            return "redirect:/cart";
        }
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("cartProducts", cartProducts);
        return "/home/cart";
    }

    @PostMapping("/cart/removeItem")
    public String removeItemFromCart(@RequestParam("productId") Long productId) throws Exception {
        Optional<Product> optionalProduct = productService.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new Exception("Product not found");
        } else {
            Product product = optionalProduct.get();
            cartProducts.remove(product.getId());
            return "redirect:/cart";
        }
    }
}
