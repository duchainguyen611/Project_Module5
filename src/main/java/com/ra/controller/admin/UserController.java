package com.ra.controller.admin;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String index(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users",users);
        return "admin/users/mainUser";
    }

    @GetMapping("/user/changeStatus/{id}")
    public String changeStatus( @PathVariable Long id){
        userService.changeStatus(id);
        return "redirect:/admin/user";
    }

}
