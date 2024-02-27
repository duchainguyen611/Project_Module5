package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.Vendor;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import com.ra.model.service.VendorService;
import com.ra.uploadFile.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/product")
    public String index(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "admin/product/mainProduct";
    }

    @GetMapping(value = "/addProduct")
    public String add(Model model) {
        List<Vendor> vendors = vendorService.getAll();
        model.addAttribute("vendors", vendors);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/product/addProduct";
    }

    @PostMapping(value = "/insertProduct")
    public String save(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file) {
//        storageService.store(file);
        product.setImage(String.valueOf(file));
        product.setCreatedAt(new Date(new java.util.Date().getTime()));
        product.setSku(UUID.randomUUID().toString());
        boolean check = productService.add(product);
        if (check) {
            return "redirect:/admin/product";
        }
        return "admin/product/mainProduct";
    }

    @GetMapping(value = "/updateProduct/{id}")
    public String update(Model model, @PathVariable Long id) {
        List<Vendor> vendors = vendorService.getAll();
        model.addAttribute("vendors", vendors);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/product/updateProduct";
    }

    @PostMapping(value = "/updateProduct")
    public String edit(@ModelAttribute("product") Product product) {
        product.setUpdateAt(new Date(new java.util.Date().getTime()));
        boolean check = productService.update(product);
        if (check) {
            return "redirect:/admin/product";
        }
        return "admin/product/mainProduct";
    }

    @GetMapping("/productDetail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        List<Vendor> vendors = vendorService.getAll();
        model.addAttribute("vendors", vendors);
        return "admin/product/productDetail";
    }

    @GetMapping("/deleteProduct/{id}")
    public String delete(@PathVariable Long id) {
        if (productService.delete(id)) {
            return "redirect:/admin/product";
        }
        return "admin/product/mainProduct";
    }
}
