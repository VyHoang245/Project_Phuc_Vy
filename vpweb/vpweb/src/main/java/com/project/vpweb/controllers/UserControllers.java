package com.project.vpweb.controllers;

import com.project.vpweb.DTO.OrderRequest;
import com.project.vpweb.models.*;
import com.project.vpweb.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserControllers {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopppingCartService shoppingCartService;
    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "store-shop";
    }

    @GetMapping("/about")
    public String about(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
        return "store-about";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
        return "store-faq";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        return "store-blog";
    }

    @GetMapping("/blog-detail")
    public String blogDetail(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
        return "store-blog-single-classic";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "store-contact";
    }

    @GetMapping("/cart")
    public String manageCarts(Model model) {
        List<ShoppingCart> cartList = shoppingCartService.getShoppingCartByUserId(1);
        model.addAttribute("cartList", cartList);
        return "store-cart";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id) {
        shoppingCartService.deleteShoppingCart(id);

        return "redirect:/shopping-Cart";
    }

    //Check out
    @GetMapping("/checkout")
    public String checkOutPage(Model model) {
        model.addAttribute("orderRequest", new OrderRequest());
        List<ShoppingCart> carts = shoppingCartService.getShoppingCartByUserId(1);
        model.addAttribute("carts", carts);
        return "store-checkout";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(Order order) {
//        productService.saveOrUpdateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/myAccount")
    public String myAccount(Model model) {
        return "store-account";
    }
}
