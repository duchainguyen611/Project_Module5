package com.ra.controller.users;

import com.ra.model.entity.Product;
import com.ra.model.entity.ShoppingCart;
import com.ra.model.entity.User;
import com.ra.model.entity.WishList;
import com.ra.model.service.ProductService;
import com.ra.model.service.ShoppingCartService;
import com.ra.model.service.WishListService;
import com.ra.security.UserDetail.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private UserLogin userLogin;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @GetMapping("/wishList")
    public String shoppingCart(Model model){
        User user = userLogin.userLogin();
        List<WishList> wishLists = wishListService.getAllByUser(user);
        model.addAttribute("wishLists",wishLists);
        return "home/wishList/wishlist";
    }

    @GetMapping("/wishList/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id){
        User user = userLogin.userLogin();
        wishListService.deleteByUserAndId(user,id);
        return "redirect:/user/wishList";
    }

    @PostMapping("/addProductToWishList/{productId}")
    public String addProductToWishList(@PathVariable Long productId){
        User user = userLogin.userLogin();
        Product product = productService.findById(productId);
        WishList wishListOld = wishListService.findByProductAndUser(product, user);
        if (wishListOld == null) {
            WishList wishList = new WishList();
            wishList.setProduct(product);
            wishList.setUser(user);
            wishListService.save(wishList);
        }
        return "redirect:/shop";
    }

    @PostMapping("/addProductFromWishListToShoppingCart/{productId}")
    public String addProductToShoppingCart(@PathVariable Long productId) {
        User user = userLogin.userLogin();
        Product product = productService.findById(productId);
        ShoppingCart oldCart = shoppingCartService.findByProductAndUser(product, user);
        if (oldCart != null) {
            oldCart.setOrderQuantity(1 + oldCart.getOrderQuantity());
            shoppingCartService.save(oldCart);
        }else {
            ShoppingCart cart = new ShoppingCart();
            cart.setProduct(productService.findById(productId));
            cart.setOrderQuantity(1);
            cart.setUser(user);
            shoppingCartService.save(cart);
        }
        return "redirect:/user/wishList";
    }
}
