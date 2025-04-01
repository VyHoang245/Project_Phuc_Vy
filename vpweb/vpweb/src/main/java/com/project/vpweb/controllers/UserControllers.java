package com.project.vpweb.controllers;

import com.project.vpweb.DTO.OrderRequest;
import com.project.vpweb.DTO.UserDTO;
import com.project.vpweb.models.*;
import com.project.vpweb.repository.RoleRepository;
import com.project.vpweb.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserControllers {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setUserName(userDTO.getUsername());
        userModel.setPassword(userDTO.getPassword());
        userModel.setEmail(userDTO.getEmail());

        Role userRole = roleService.getRoleByName("ROLE_USER");
        if (userRole == null) {
            throw new RuntimeException("Default role USER not found in database!");
        }
        userModel.setRoles(Set.of(userRole));
        userModel.setAddress("");
        userModel.setFullName("");
        userService.addUser(userModel);
        return "redirect:/login";
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "store";
    }

    @GetMapping("/product-detail")
    public String productDetail(Model model) {
        return "store-single-product";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "store-shop";
    }

    @GetMapping("/about")
    public String about() {
        return "store-about";
    }

    @GetMapping("/faq")
    public String faq() {
        return "store-faq";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        return "store-blog";
    }

    @GetMapping("/blog-detail")
    public String blogDetail() {
        return "store-blog-single-classic";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "store-contact";
    }

    @GetMapping("/cart")
    public String manageCarts(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        if (user == null) {
            return "redirect:/login";
        }
        List<ShoppingCart> cartList = shoppingCartService.getShoppingCartByUserId(user.getId());
        model.addAttribute("cartList", cartList);
        return "store-cart";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id) {
        shoppingCartService.deleteShoppingCart(id);

        return "redirect:/cart";
    }

    //Check out
    @GetMapping("/checkout")
    public String checkOutPage(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        model.addAttribute("orderRequest", new OrderRequest());
        List<ShoppingCart> carts = shoppingCartService.getShoppingCartByUserId(user.getId());
        model.addAttribute("carts", carts);
        return "store-checkout";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(Order order) {
//        productService.saveOrUpdateProduct(product);
        return "redirect:/admin/products";
    }
    //Order
    @PostMapping("/submit")
    public String submitOrder(OrderRequest orderRequest, @AuthenticationPrincipal CustomUserDetails user) {
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
        return "redirect:/myAccount";
    }

    @GetMapping("/myAccount")
    public String myAccount(Model model,@AuthenticationPrincipal CustomUserDetails user) {
        if (user == null) {
            return "redirect:/login";
        }
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("orderDetails",orderDetailsService.getAllOrderDetails());
        return "store-account";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "editProduct";
    }
}
