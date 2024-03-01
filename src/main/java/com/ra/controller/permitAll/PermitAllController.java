package com.ra.controller.permitAll;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.model.entity.Vendor;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import com.ra.model.service.UserService;
import com.ra.model.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PermitAllController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VendorService vendorService;

    @GetMapping("/login")
    public String login() {
        return "home/authentication/login";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){
        return "home/aboutUs/aboutUs";
    }

    @GetMapping("/blog")
    public String blog(){
        return "home/blog/blog";
    }

    @GetMapping("/contact")
    public String contact(){
        return "home/contact/contactUs";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "home/authentication/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("")
    public String home() {
        return "home/index";
    }

    @GetMapping("/shop")
    public String shopProduct(Model model,
                              @RequestParam(defaultValue = "16", name = "limit") int limit,
                              @RequestParam(defaultValue = "0", name = "page") int page,
                              @RequestParam(defaultValue = "id|asc", name = "sort") String sort) {

        String[] sortArray = sort.split("\\|");
        String sortField = sortArray[0];
        String sortOrder = sortArray[1];

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortField));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            pageable = PageRequest.of(page, limit, Sort.by(sortField).descending());
        }

        List<Category> categories = categoryService.getAll();
        List<Vendor> vendors = vendorService.getAll();
        Page<Product> products = productService.getAllProductHome(pageable);

        model.addAttribute("categories", categories);
        model.addAttribute("vendors", vendors);
        model.addAttribute("products", products);

        return "home/Product/shopProduct";
    }



    @GetMapping("/product/productDetail/{id}")
        public String productDetail (Model model, @PathVariable Long id){
            Product product = productService.findById(id);
            model.addAttribute("product", product);
            return "home/Product/productDetailhome";
        }

        @GetMapping("/product/getAllByCategory/{id}")
        public String getAllByCategory (Model model, @PathVariable Long id){
            List<Category> categories = categoryService.getAll();
            List<Vendor> vendors = vendorService.getAll();
            model.addAttribute("categories", categories);
            model.addAttribute("vendors", vendors);
            Category category = categoryService.findById(id);
            List<Product> products = productService.findByCategory(category);
            model.addAttribute("products", products);
            return "home/Product/shopProduct";
        }

        @GetMapping("/product/getAllByVendor/{id}")
        public String getAllByVendor (Model model, @PathVariable Long id){
            List<Category> categories = categoryService.getAll();
            List<Vendor> vendors = vendorService.getAll();
            model.addAttribute("categories", categories);
            model.addAttribute("vendors", vendors);
            Vendor vendor = vendorService.findById(id);
            List<Product> products = productService.findByVendor(vendor);
            model.addAttribute("products", products);
            return "home/Product/shopProduct";
        }

        @GetMapping("/product/search")
        public String search (Model model, @RequestParam("keyWord") String keyWord){
            List<Category> categories = categoryService.getAll();
            List<Vendor> vendors = vendorService.getAll();
            model.addAttribute("categories", categories);
            model.addAttribute("vendors", vendors);
            List<Product> products = productService.findAllByProductName(keyWord);
            model.addAttribute("products", products);
            return "home/Product/shopProduct";
        }




    }
