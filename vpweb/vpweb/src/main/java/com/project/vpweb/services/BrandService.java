package com.project.vpweb.services;
import com.project.vpweb.models.Brand;
import com.project.vpweb.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(int id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand saveBrand(Brand Brand) {
        return brandRepository.save(Brand);
    }

    public void deleteBrand(int id) {
        brandRepository.deleteById(id);
    }


}
