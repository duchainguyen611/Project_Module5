package com.ra.controller.permitAll;
import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PermitAllController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "home/authentication/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute(user);
        return "home/authentication/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        userService.register(user);
        return "redirect:/login";
    }


}
