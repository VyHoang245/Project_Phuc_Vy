package com.controllers;

import com.DTO.OrderRequest;
import com.models.*;
import com.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductControllers {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopppingCartService shoppingCartService;

    @Autowired
    private ImageService imageService;

    @GetMapping({"/", ""})
    public String getAllProducts(Model model) {
        System.out.println(productService.getAllProducts());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "store";
    }

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

    @GetMapping("/login")
    public String login(Model model) {
        return "store-login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "store-registration";
    }

    @GetMapping("/product-detail")
    public String productDetail(Model model) {
        return "store-single-product";
    }

    @GetMapping("/getProducts")
    public String geProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "ViewProducts";
    }

    @GetMapping("/addProduct")
    public String AddUser(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "addProduct";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
//        model.addAttribute("product", new Product());
//        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin";
    }

    @GetMapping("/manage-products")
    public String manageProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "manage-products";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestParam("file") MultipartFile file, Product product) throws IOException {
        productService.saveOrUpdateProduct(file, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "EditProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);

        return "redirect:/admin/products";
    }

    @PutMapping("/editSaveProduct")
    public String editSaveProduct(@RequestParam("file") MultipartFile file,@ModelAttribute Product product
    ) throws IOException {
        productService.saveOrUpdateProduct( file, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/list-products";
    }

    //	User management
    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @GetMapping("/edit-users")
    public String editUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userEdit";
    }

    // Category management
    @GetMapping("/manage-category")
    public String manageCategories(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category-mangement";
    }

    @GetMapping("/addCategory")
    public String AddCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category-add";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/manage-category";
    }

    @GetMapping("/editCategory/{id}")
    public String editCategory(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category-edit";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);

        return "redirect:/manage-category";
    }

    @PutMapping("/editSaveCategory")
    public String editSaveCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);

        return "redirect:/manage-category";
    }

    //Brand management
    @GetMapping("/manage-Brand")
    public String manageBrands(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("brand", brandService.getAllBrands());
        return "brand-mangement";
    }

    @GetMapping("/editBrand/{id}")
    public String editBrand(@PathVariable int id, Model model) {
        model.addAttribute("brand", brandService.getBrandById(id));
        model.addAttribute("brands", brandService.getAllBrands());
        return "brand-edit";
    }

    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);

        return "redirect:/manage-brand";
    }

    @PutMapping("/editSaveBrand")
    public String editSaveBrand(@ModelAttribute Brand brand) {
        brandService.saveBrand(brand);

        return "redirect:/manage-brand";
    }

    @PostMapping("/saveBrand")
    public String saveBrand(Brand brand) {
        brandService.saveBrand(brand);
        return "redirect:/manage-brand";
    }

    // Cart management
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


