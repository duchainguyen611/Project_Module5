package com.ra.model.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Boolean add(Category category);
    Boolean update(Category category);

    Boolean delete(Long Id);

    Category findById(Long Id);
}
