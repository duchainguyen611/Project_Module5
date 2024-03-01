package com.ra.model.service;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product);
    void delete(Long Id);
    Product findById(Long Id);
    List<Product> findByCategory(Category category);
    List<Product> findByVendor(Vendor vendor);
    List<Product> findAllByProductName(String productName);
    Page<Product> getAllProductHome(Pageable pageable);
}
