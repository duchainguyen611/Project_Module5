package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/category")
    public String index(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "admin/category/mainCategory";
    }

    @GetMapping("/addCategory")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/addCategory";
    }

    @PostMapping("/insertCategory")
    public String save(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "redirect:/admin/addCategory";
        }
        log.info(category.toString());
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/updateCategory/{id}")
    public String update(Model model, @PathVariable Long id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category/updateCategory";
    }

    @PostMapping("/editCategory")
    public String edit(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/admin/category";

    }

}
