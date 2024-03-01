package com.ra.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserPersonalController {
    @GetMapping("/profile")
    public String profileUser(){
        return "home/profile/my-account";
    }

    @GetMapping("")
    public String home() {
        return "home/index";
    }
}
