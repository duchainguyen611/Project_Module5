package com.ra.model.service;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Boolean add(Product product);
    Boolean update(Product product);

    Boolean delete(Long Id);

    Product findById(Long Id);
}
