package com.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.Product;
import com.models.Category;
import com.services.ProductRepository;
import com.services.ProductService;
import com.services.CategoryService;
import com.services.CategoryRepository;

import java.util.*;

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
        return "index"; 
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
    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "store-shop";
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
