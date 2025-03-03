package com.controllers;

import com.models.Brand;
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

//    @RequestMapping(value = "/increaseAndSaveCart/{id}", method = RequestMethod.GET)


    @GetMapping("/increaseAndSaveCart/{id}")
    @ResponseBody
    public ShoppingCart increaseAndSaveCart(@PathVariable int id) {
        ShoppingCart cart = shoppingCartService.getShoppingCartById(id);
        cart.setQuantity(cart.getQuantity() + 1);
        shoppingCartService.saveShoppingCart(cart);
        return cart;
    }

//    @PostMapping("/increaseAndSaveCart/{id}")
//    @ResponseBody
//    public ResponseEntity<ShoppingCart> increaseAndSaveCart(@PathVariable int id) {
//        ShoppingCart cart = shoppingCartService.getShoppingCartById(id);
//        if (cart == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        int currentQuantity = cart.getQuantity();
//        int maxQuantity = cart.getProduct().getQuantityInStock();
//
//        if (currentQuantity < maxQuantity) {
//            cart.setQuantity(currentQuantity + 1);
//            shoppingCartService.saveShoppingCart(cart);
//
//            return new ResponseEntity<>(cart, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Or any other suitable status code
//        }
//    }
//


}
