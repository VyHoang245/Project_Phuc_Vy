package com.project.vpweb.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import com.project.vpweb.models.Image;
import com.project.vpweb.models.Product;
import com.project.vpweb.repository.ImageRepository;
import com.project.vpweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Value(value = "${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ImageRepository imageRepository;

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
    
//    private Product convertToEntity(ProductDTO productDTO) {
//        Product product = new Product();
//        product.setId(product.getId());
//        product.setName(product.getName());
//        product.setBrand(product.getBrand());
//        product.setPrice(product.getPrice());
//        product.setImageLink(product.getImageLink());
//        product.setDescription(product.getDescription());
//        //convert from name to Category obj
//        //product.setCategory(product.g().getId());
//        return product;
//    }

    
    public Product getProductById(int id) {
    	Product product = productRepository.findById(id).orElse(null);
    	return product;
    }

    public void saveOrUpdateProduct(MultipartFile file, Product product) throws IOException {
//        try {
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Image image = new Image();
            image.setPath(fileName);
            imageRepository.save(image);

            product.setImageLink(fileName);
        }

        productRepository.save(product);
//        } catch (IOException e) {
//            throw new RuntimeException("Error saving product with image: " + e.getMessage());
//        }
    }
    
    public void deleteProduct(int id) {
    	productRepository.deleteById(id);
    }
    
//    public Product editProduct(int id) {
//    	Product product = productRepository.findById(id).orElse(null);
//    	return productRepository.save(product);
//    }
}
