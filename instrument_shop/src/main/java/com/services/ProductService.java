package com.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.ProductDTO;
import com.models.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
       // return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
//    private ProductDTO convertToDTO(Product product) {
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setId(product.getId());
//        productDTO.setName(product.getName());
//        productDTO.setBrand(product.getBrand());
//        productDTO.setPrice(product.getPrice());
//        productDTO.setImageLink(product.getImageLink());
//        productDTO.setDescription(product.getDescription());
//        productDTO.setCategory_name(product.getCategory().getName());
//        return productDTO;
//    }
    
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(product.getId());
        product.setName(product.getName());
        product.setBrand(product.getBrand());
        product.setPrice(product.getPrice());
        product.setImageLink(product.getImageLink());
        product.setDescription(product.getDescription());
        //convert from name to Category obj
        //product.setCategory(product.g().getId());
        return product;
    }

    
    public Product getProductById(int id) {
    	Product product = productRepository.findById(id).orElse(null);
    	return product;
    }
    
    public Product saveOrUpdateProduct(Product product) {
    	return productRepository.save(product);
    }
    
    public void deleteProduct(int id) {
    	productRepository.deleteById(id);
    }
    
//    public Product editProduct(int id) {
//    	Product product = productRepository.findById(id).orElse(null);
//    	return productRepository.save(product);
//    }
}
