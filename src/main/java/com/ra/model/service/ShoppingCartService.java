package com.ra.model.service;

import com.ra.model.entity.Product;
import com.ra.model.entity.ShoppingCart;
import com.ra.model.entity.User;
import com.ra.model.entity.dto.CheckOutInfor;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getAllByUser(User user);
    void deleteByUserAndId(User user,Long id);
    ShoppingCart save(ShoppingCart cart);
    ShoppingCart findById(Long id);
    void updateOrderQuantity(Long id,Integer quantity);
    void deleteByUser(User user);
    void checkOut(CheckOutInfor checkOutInfor);
    ShoppingCart findByProductAndUser(Product product, User user);
}
