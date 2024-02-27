package com.ra.controller.admin;

import com.ra.model.entity.Vendor;
import com.ra.model.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @GetMapping("/vendor")
    public String index(Model model){
        List<Vendor> vendors = vendorService.getAll();
        model.addAttribute("vendors",vendors);
        return "admin/vendor/mainVendor";
    }

    @GetMapping(value = "/addVendor")
    public String add(Model model) {
        Vendor vendor = new Vendor();
        model.addAttribute("vendor", vendor);
        return "admin/vendor/addVendor";
    }

    @PostMapping(value = "/insertVendor")
    public String save(@ModelAttribute("vendor")Vendor vendor) {
        boolean check = vendorService.add(vendor);
        if(check) {
            return "redirect:/admin/vendor";
        }
        return "admin/vendor/mainVendor";
    }

    @GetMapping(value = "/updateVendor/{id}")
    public String update(Model model,@PathVariable Long id) {
        Vendor vendor = vendorService.findById(id);
        model.addAttribute("vendor", vendor);
        return "admin/vendor/updateVendor";
    }

    @PostMapping(value = "/editVendor")
    public String edit(@ModelAttribute("vendor")Vendor vendor) {
        boolean check = vendorService.update(vendor);
        if(check) {
            return "redirect:/admin/vendor";
        }
        return "admin/vendor/mainVendor";
    }

    @GetMapping("/deleteVendor/{id}")
    public String delete(@PathVariable Long id) {

        if(vendorService.delete(id)) {
            return "redirect:/admin/vendor";
        }
        return "admin/vendor/mainVendor";
    }
}
