package com.ra.model.serviceImp;

import com.ra.model.entity.ShoppingCart;
import com.ra.model.entity.User;
import com.ra.model.repository.ShoppingCartRepository;
import com.ra.model.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Override
    public List<ShoppingCart> getAllByUser(User user) {
        return shoppingCartRepository.getAllByUser(user);
    }
    @Transactional
    @Override
    public void deleteByUserAndId(User user, Long id) {
        shoppingCartRepository.deleteShoppingCartByIdAndUser(id,user);
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    public void updateOrderQuantity(Long id, Integer quantity) {
        ShoppingCart cart = findById(id);
        cart.setOrderQuantity(quantity);
        save(cart);
    }


}
