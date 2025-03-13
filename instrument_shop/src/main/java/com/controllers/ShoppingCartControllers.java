package com.controllers;

import com.DTO.OrderDetailRequest;
import com.DTO.OrderRequest;
import com.models.*;
import com.services.OrderService;
import com.services.ProductService;
import com.services.ShopppingCartService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public String addAndSaveCart(@PathVariable int productID) {
        User user;
        user = userService.getUserById(1);
        Product product = productService.getProductById(productID);
        ShoppingCart cart;
        String result = "Fail";
        if(!shoppingCartService.checkExist(productID, user)){
            cart = new ShoppingCart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(1);
        }
        else{
            cart = shoppingCartService.increaseQuantityProduct(productID);
        }
        if(cart != null){
            result = "successful";
        }
        shoppingCartService.saveShoppingCart(cart);
        return result;
    }

//    @RequestMapping(value = "/increaseAndSaveCart/{id}", method = RequestMethod.GET)


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
public ResponseEntity<String> submitOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setUser(userService.getUserById(1));
    order.setFullName(orderRequest.getFirstName()+orderRequest.getLastName());
    order.setPhone(orderRequest.getPhone());
    order.setAddress(orderRequest.getStreet()+"-"+orderRequest.getWard()+"-"+orderRequest.getCity()+"-"+orderRequest.getProvince());
    order.setTotalPrice(orderRequest.getTotalPrice());
    order.setNote(orderRequest.getNotes());
//    order.setPaymentMethod(orderRequest.getPaymentMethod());

    List<OrderDetail> orderDetails = new ArrayList<>();
    for (ShoppingCart cart : orderRequest.getCarts()) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(productService.getProductById(cart.getProduct().getId()));
        orderDetail.setQuantity(cart.getQuantity());
        orderDetails.add(orderDetail);
    }

    orderService.saveOrder(order, orderDetails);
    return ResponseEntity.ok("Order placed successfully");
}

}
