package com.ra.model.serviceImp;

import com.ra.model.entity.Category;
import com.ra.model.repository.CategoryRepository;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long Id) {
        categoryRepository.deleteById(Id);
    }


    @Override
    public Category findById(Long Id) {
        return categoryRepository.findById(Id).orElse(null);
    }
}
