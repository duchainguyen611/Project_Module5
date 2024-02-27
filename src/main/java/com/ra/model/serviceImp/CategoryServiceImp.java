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
    public Boolean add(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long Id) {
        try {
            categoryRepository.deleteById(Id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findById(Long Id) {
        return categoryRepository.findById(Id).orElse(null);
    }
}
