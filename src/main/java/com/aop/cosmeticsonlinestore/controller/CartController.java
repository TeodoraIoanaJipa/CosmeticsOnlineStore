package com.aop.cosmeticsonlinestore.controller;

import com.aop.cosmeticsonlinestore.model.*;
import com.aop.cosmeticsonlinestore.model.request.OrderRequest;
import com.aop.cosmeticsonlinestore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class CartController {

    private HashMap<Long, Product> cartProducts = new HashMap<>();
    private final ProductService productService;
    private final UserService userService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @Autowired
    public CartController(
            ProductService productService,
            UserService userService,
            AddressService addressService,
            OrderService orderService,
            OrderItemService orderItemService
    ) {
        this.productService   = productService;
        this.userService      = userService;
        this.addressService   = addressService;
        this.orderService     = orderService;
        this.orderItemService = orderItemService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId) throws Exception {
        Optional<Product> optionalProduct = productService.findById(productId);
        if (!optionalProduct.isPresent()) {
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
        if (!optionalProduct.isPresent()) {
            throw new Exception("Product not found");
        } else {
            Product product = optionalProduct.get();
            cartProducts.remove(product.getId());
            return "redirect:/cart";
        }
    }

    @GetMapping("/order")
    public String getOrder(Model model) {
        model.addAttribute("products", cartProducts);
        model.addAttribute("validOrder", new OrderRequest());
        return "/home/order";
    }

    @PostMapping("/order")
    public String saveOrder(@Valid OrderRequest validOrder, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("products", cartProducts);
            model.addAttribute("validOrder", validOrder);
            return "/home/order";
        } else {
            Optional<User> optionalUser = userService.findById(Long.valueOf(2));
            User user = optionalUser.get();

            Address address = new Address();
            address.setCounty(validOrder.getCounty());
            address.setCity(validOrder.getCity());
            address.setStreet(validOrder.getStreet());
            address.setPostalCode(validOrder.getPostalCode());
            Address newAddress = addressService.save(address);

            Order order = new Order();
            order.setFirstName(validOrder.getFirstName());
            order.setLastName(validOrder.getLastName());
            order.setPhoneNumber(validOrder.getPhoneNumber());
            order.setOrderDate(new Date());
            order.setUser(user);
            order.setAddress(newAddress);
            Order newOrder = orderService.save(order);

            for (Map.Entry<Long, Product> cartProduct : cartProducts.entrySet()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(newOrder);
                orderItem.setUser(user);
                orderItem.setProduct(cartProduct.getValue());
                orderItemService.save(orderItem);
            }
            return "redirect:/finalizeOrder";
        }
    }

    @GetMapping("/finalizeOrder")
    public String finalizeOrder() {
        return "/home/finzalize_order";
    }

    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
