package com.ra.model.serviceImp;

import com.ra.model.entity.User;
import com.ra.model.entity.WishList;
import com.ra.model.repository.WishListRepository;
import com.ra.model.service.WishListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishListServiceImp implements WishListService {
    @Autowired
    private WishListRepository wishListRepository;
    @Override
    public List<WishList> getAllByUser(User user) {
        return wishListRepository.getAllByUser(user);
    }
    @Transactional
    @Override
    public void deleteByUserAndId(User user, Long id) {
        wishListRepository.deleteWishListByUserAndId(user,id);
    }

    @Override
    public WishList save(WishList wishList) {
        return wishListRepository.save(wishList);
    }

    @Override
    public WishList findByIdAndUser(Long id, User user) {
        return wishListRepository.findByIdAndUser(id,user);
    }
}
