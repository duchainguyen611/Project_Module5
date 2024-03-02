package com.ra.security.UserDetail;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLogin {
    @Autowired
    private UserService userService;
    public User userLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
            return userService.findById(userPrinciple.getUser().getId());
        } else {
            return null;
        }
    }
}
