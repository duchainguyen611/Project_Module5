package com.ra.controller.users;

import com.ra.model.entity.Invoice;
import com.ra.model.entity.InvoiceDetail;
import com.ra.model.entity.User;
import com.ra.model.service.InvoiceDetailService;
import com.ra.model.service.InvoiceService;
import com.ra.model.service.UserService;
import com.ra.security.UserDetail.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPersonalController {
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLogin userLogin;
    @Value("${path-upload}")
    private String pathUpload;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String profileUser(Model model){
        User user = userLogin.userLogin();
        if (user.getFullName()==null){
            return "redirect:/user/updateProfile";
        }
        List<Invoice> invoiceList = invoiceService.findAllByUser(user);
        model.addAttribute("invoiceList",invoiceList);
        model.addAttribute("user",user);
        return "home/profile/my-account";
    }

    @GetMapping("/invoiceDetail/{id}")
    public String invoiceDetail(Model model, @PathVariable Long id){
        User user = userLogin.userLogin();
        Invoice invoice = invoiceService.findById(id);
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAllByInvoice(invoice);
        model.addAttribute("user",user);
        model.addAttribute("invoice",invoice);
        model.addAttribute("invoiceDetails",invoiceDetails);
        return "home/invoice/invoice";
    }

    @GetMapping("/updateProfile")
    public String updateProfile(Model model){
        User user = userLogin.userLogin();
        model.addAttribute("user",user);
        return "home/profile/account-info-edit";
    }

    @PostMapping("/editInformationProfile")
    public String editInformationProfile(@ModelAttribute("user")User user, @RequestParam("imageUser") MultipartFile file){
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(pathUpload + fileName));
            user.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User userOld = userLogin.userLogin();
        user.setPassword(userOld.getPassword());
        user.setStatus(userOld.getStatus());
        user.setUsername(userOld.getUsername());
        user.setCreatedAt(userOld.getCreatedAt());
        user.setUpdateAt(new Date(new java.util.Date().getTime()));
        user.setRoles(userOld.getRoles());
        user.setUpdateAt(new Date(new java.util.Date().getTime()));
        userService.update(user);
        return "redirect:/user/profile";
    }

    @PostMapping("/editPassWordProfile")
    public String updatePasswordAdmin(Model model
            , @RequestParam("oldPassword") String oldPassword
            , @RequestParam("newPassword") String newPassword
            , @RequestParam("conPassword") String conPassword) {
        User userCheck = userLogin.userLogin();
        if (!passwordEncoder.matches(oldPassword, userCheck.getPassword())) {
            model.addAttribute("errorStyle1", "display:block;color:red;");
            throw new RuntimeException();
        } else if (newPassword.equals(oldPassword)) {
            model.addAttribute("errorStyle2", "display:block;color:red;");
            throw new RuntimeException();
        } else if (!newPassword.equals(conPassword)) {
            model.addAttribute("errorStyle3", "display:block;color:red;");
            throw new RuntimeException();
        } else {
            userCheck.setUpdateAt(new Date(new java.util.Date().getTime()));
            userCheck.setPassword(passwordEncoder.encode(conPassword));
            userService.update(userCheck);
        }
        return "redirect:/user/profile";
    }

    @GetMapping("")
    public String home() {
        return "home/index";
    }

}
