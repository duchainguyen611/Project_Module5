package com.ra.controller.admin;

import com.ra.model.entity.Vendor;
import com.ra.model.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @Value("${path-upload}")
    private String pathUpload;
    @GetMapping("/vendor")
    public String index(Model model) {
        List<Vendor> vendors = vendorService.getAll();
        model.addAttribute("vendors", vendors);
        return "admin/vendor/mainVendor";
    }

    @GetMapping(value = "/addVendor")
    public String add(Model model) {
        Vendor vendor = new Vendor();
        model.addAttribute("vendor", vendor);
        return "admin/vendor/addVendor";
    }

    @PostMapping(value = "/insertVendor")
    public String save(@ModelAttribute("vendor") Vendor vendor,@RequestParam("imageVendor") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(pathUpload + fileName));
            vendor.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vendorService.save(vendor);
        return "redirect:/admin/vendor";
    }

    @GetMapping(value = "/updateVendor/{id}")
    public String update(Model model, @PathVariable Long id) {
        Vendor vendor = vendorService.findById(id);
        model.addAttribute("vendor", vendor);
        return "admin/vendor/updateVendor";
    }

    @PostMapping(value = "/editVendor")
    public String edit(@ModelAttribute("vendor") Vendor vendor,@RequestParam("imageVendor") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(pathUpload + fileName));
            vendor.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vendorService.save(vendor);
        return "redirect:/admin/vendor";
    }

    @GetMapping("/deleteVendor/{id}")
    public String delete(@PathVariable Long id) {
        vendorService.delete(id);
        return "redirect:/admin/vendor";

    }
}
