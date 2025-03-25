package com.services;

import com.models.Product;
import com.models.ShoppingCart;
import com.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getAllCarts() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart getShoppingCartById(int id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }
    public List<ShoppingCart> getShoppingCartByUserId(int id) {
        List<ShoppingCart> cartList = new ArrayList<>();
        for (ShoppingCart cart : getAllCarts()) {
            if (cart.getUser().getId() == id) {
                cartList.add(cart);
            }
        }
        return cartList;
    }
    public ShoppingCart increaseQuantityProduct(int productID) {
        List<ShoppingCart> cartList = getAllCarts();
        for (ShoppingCart cart : cartList) {
            if(cart.getProduct().getId() == productID ) {
                if(cart.getProduct().getQuantityInStock()> cart.getQuantity()) {
                    cart.setQuantity(cart.getQuantity() + 1);
                    return cart;
                }
            }
        }
        return null;
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public void deleteShoppingCart(int id) {
        shoppingCartRepository.deleteById(id);
    }

    public boolean checkExist(int productId, User user) {
        List<ShoppingCart> cartList = getAllCarts();
        for (ShoppingCart cart : cartList) {
            if(cart.getProduct().getId() ==productId && cart.getUser().getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }
}
