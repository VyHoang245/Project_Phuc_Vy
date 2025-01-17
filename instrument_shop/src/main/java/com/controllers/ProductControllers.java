package com.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.models.Product;
import com.services.ProductService;
import com.services.CategoryService;

@Controller
public class ProductControllers {
    @Autowired
	private ProductService productService;
    @Autowired
	private CategoryService categoryService;
	
	@GetMapping({"/",""})
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

    @GetMapping("/cart")
    public String cart(Model model) {
        return "store-cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        return "store-checkout";
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

    @PostMapping("/saveProduct")
    public String saveProduct(Product product) {
    	productService.saveOrUpdateProduct(product);
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
    public String editSaveProduct(@ModelAttribute Product product
    ) {
    	productService.saveOrUpdateProduct(product);

        return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/products")
    public String listProducts(Model model) {
    	model.addAttribute("products", productService.getAllProducts());
    	//model.addAttribute("categories", categoryService.getAllCategories());
    	return "admin/list-products";
    }
	
}
