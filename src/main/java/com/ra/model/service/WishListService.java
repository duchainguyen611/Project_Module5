package com.ra.model.service;

import com.ra.model.entity.Product;
import com.ra.model.entity.ShoppingCart;
import com.ra.model.entity.User;
import com.ra.model.entity.WishList;

import java.util.List;

public interface WishListService {
    List<WishList> getAllByUser(User user);
    void deleteByUserAndId(User user, Long id);
    WishList save(WishList wishList);
    WishList findByProductAndUser(Product product, User user);
}
