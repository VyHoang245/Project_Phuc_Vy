package com.project.vpweb.controllers;


import com.project.vpweb.DTO.OrderRequest;
import com.project.vpweb.models.*;
import com.project.vpweb.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ShoppingCartControllers {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/addAndSaveCart/{productID}", method = RequestMethod.GET)
    public String addAndSaveCart(@PathVariable int productID, @AuthenticationPrincipal CustomUserDetails user) {
        Product product = productService.getProductById(productID);
        ShoppingCart cart;
        String result = "Fail";
        UserModel userModel = userService.getUserByUsername(user.getUsername());
        if (!shoppingCartService.checkExist(productID, userModel)) {
            cart = new ShoppingCart();
            cart.setProduct(product);
            cart.setUser(userModel);
            cart.setQuantity(1);
        } else {
            cart = shoppingCartService.increaseQuantityProduct(productID);
        }
        if (cart != null) {
            result = "successful";
        }
        shoppingCartService.saveShoppingCart(cart);
        return result;
    }

    @GetMapping("/increaseAndSaveCart/{id}")
    @ResponseBody
    public ShoppingCart increaseAndSaveCart(@PathVariable int id) {
        ShoppingCart cart = shoppingCartService.getShoppingCartById(id);
        cart.setQuantity(cart.getQuantity() + 1);
        shoppingCartService.saveShoppingCart(cart);
        return cart;
    }

    @PostMapping("/increaseAndSaveCart/{id}/{quantity}")
    @ResponseBody
    public ResponseEntity<String> increaseAndSaveCart(@PathVariable int id, @PathVariable int quantity) {
        ShoppingCart cart = shoppingCartService.getShoppingCartById(id);
        cart.setQuantity(quantity);
        shoppingCartService.saveShoppingCart(cart);

        return ResponseEntity.ok("Quantity updated successfully");

    }

    //Order
    @PostMapping("/submit")
    public ResponseEntity<String> submitOrder(OrderRequest orderRequest, @AuthenticationPrincipal CustomUserDetails user) {
        Order order = new Order();
        UserModel userModel = userService.getUserByUsername(user.getUsername());
        order.setUser(userModel);
        order.setFullName(orderRequest.getFirstName() + " " + orderRequest.getLastName());
        order.setPhone(orderRequest.getPhone());
        order.setAddress(orderRequest.getStreet() + " - " + orderRequest.getWard() + " - " + orderRequest.getCity() + " - " + orderRequest.getProvince());
        order.setOrderDate(String.valueOf(LocalDate.now()));
        order.setOrderStatus("Pending");
        order.setNote(orderRequest.getNotes());
//    order.setPaymentMethod(orderRequest.getPaymentMethod());

        List<ShoppingCart> carts = shoppingCartService.getShoppingCartByUserId(user.getId());
        List<OrderDetail> orderDetails = new ArrayList<>();
        double totalPrice = 0;
        for (ShoppingCart cart : carts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(productService.getProductById(cart.getProduct().getId()));
            orderDetail.setQuantity(cart.getQuantity());
            orderDetails.add(orderDetail);
            totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
        }
        order.setTotalPrice(totalPrice);
        orderService.saveOrder(order, orderDetails);
        return ResponseEntity.ok("Order placed successfully");
    }

}
