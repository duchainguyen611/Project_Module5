package com.ra.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class WishListController {
    @GetMapping("/wishList")
    public String shoppingCart(){
        return "home/wishList/wishlist";
    }
}
