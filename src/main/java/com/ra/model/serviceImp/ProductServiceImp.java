package com.ra.model.serviceImp;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.Vendor;
import com.ra.model.repository.ProductRepository;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long Id) {
        productRepository.deleteById(Id);
    }


    @Override
    public Product findById(Long Id) {
        return productRepository.findById(Id).orElse(null);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.getAllByCategory(category);
    }

    @Override
    public List<Product> findByVendor(Vendor vendor) {
        return productRepository.getAllByVendor(vendor);
    }

    @Override
    public List<Product> findAllByProductName(String productName) {
        return productRepository.findAllByProductNameContainingIgnoreCase(productName);
    }

    @Override
    public Page<Product> getAllProductHome(Pageable pageable) {
        return productRepository.getAllProductHome(pageable);
    }

}
