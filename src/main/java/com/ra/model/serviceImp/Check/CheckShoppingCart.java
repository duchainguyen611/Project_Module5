package com.ra.model.serviceImp.Check;

import com.ra.model.repository.ShoppingCartRepository;
import com.ra.security.UserDetail.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckShoppingCart {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserLogin userLogin;



}
