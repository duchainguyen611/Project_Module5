package com.ra.controller.admin;

import com.ra.model.entity.User;
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
@RequestMapping("/admin")
public class UserManagerController {
    @Autowired
    private UserService userService;

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


}
