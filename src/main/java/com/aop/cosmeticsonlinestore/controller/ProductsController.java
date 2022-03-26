package com.aop.cosmeticsonlinestore.controller;

import com.aop.cosmeticsonlinestore.model.Product;
import com.aop.cosmeticsonlinestore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {
    
    private Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Page<Product> getProduct(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable) {
        try {
            Page<Product> products = productService.findAllProductsPageable(pageable);
            return products; 
        } catch(Exception exception){
            logger.debug("Exception occured");
        }
        return null;
    }

    @GetMapping("/")
    public String home(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable, Model model) {
        Page<Product> products = productService.findAllProductsPageable(pageable);
        model.addAttribute("products", products);

        return "/home/main";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<Product> optionalProduct = productService.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
            return "/home/view";
        } else {
            throw new Exception("Product not found");
        }
    }
}
