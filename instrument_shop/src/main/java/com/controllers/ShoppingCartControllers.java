package com.controllers;

import com.models.Product;
import com.models.ShoppingCart;
import com.models.User;
import com.services.ProductService;
import com.services.ShopppingCartService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartControllers {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopppingCartService shoppingCartService;

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
}
