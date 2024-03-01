package com.ra.controller.admin;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
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
@RequestMapping("/admin")
public class UserManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${path-upload}")
    private String pathUpload;

    @GetMapping("/user")
    public String index(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "admin/users/mainUser";
    }

    @GetMapping("/user/changeStatus/{id}")
    public String changeStatus(@PathVariable Long id) {
        userService.changeStatus(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/profile")
    public String profileAdmin(Model model) {
        User user = userService.findById(1L);
        model.addAttribute("user", user);
        return "admin/inforAdmin/adminProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfileAdmin(@ModelAttribute("user") User user, @RequestParam("imageAdmin") MultipartFile file) {
        User userOld = userService.findById(1L);
        user.setPassword(userOld.getPassword());
        user.setStatus(userOld.getStatus());
        user.setUsername(userOld.getUsername());
        user.setCreatedAt(userOld.getCreatedAt());
        user.setUpdateAt(new Date(new java.util.Date().getTime()));
        user.setRoles(userOld.getRoles());
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(pathUpload + fileName));
            user.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userService.update(user);
        return "redirect:/admin/profile";
    }

    @PostMapping("/updatePassword")
    public String updatePasswordAdmin(Model model
            , @RequestParam("oldPassword") String oldPassword
            , @RequestParam("newPassword") String newPassword
            , @RequestParam("conPassword") String conPassword) {
        User userCheck = userService.findById(1L);
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
        return "redirect:/admin/profile";
    }
}
