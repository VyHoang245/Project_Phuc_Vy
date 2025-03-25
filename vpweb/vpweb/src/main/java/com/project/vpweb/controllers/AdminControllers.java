package com.project.vpweb.controllers;

import com.project.vpweb.DTO.UserDTO;
import org.springframework.ui.Model;
import com.project.vpweb.services.*;
import com.project.vpweb.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class AdminControllers {
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

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "store";
    }
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
        userModel.setRole("USER");
        userModel.setAddress("");
        userModel.setFullName("");
       userService.addUser(userModel);
        return "redirect:/login";
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

}
