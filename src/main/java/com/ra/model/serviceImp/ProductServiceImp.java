package com.ra.model.serviceImp;

import com.ra.model.entity.Product;
import com.ra.model.repository.ProductRepository;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Boolean add(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long Id) {
        try {
            productRepository.deleteById(Id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(Long Id) {
        return productRepository.findById(Id).orElse(null);
    }

}
