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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class ProductDetailController {
    @Autowired
    private UserLogin userLogin;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private WishListService wishListService;


    @PostMapping("/addProductFromProductDetailToShoppingCart/{productId}")
    public String addProductToShoppingCart(@PathVariable Long productId,
                                           @RequestParam(name = "quantityProduct")Integer quantityProduct) {
        User user = userLogin.userLogin();
        ShoppingCart cart = new ShoppingCart();
        cart.setProduct(productService.findById(productId));
        cart.setOrderQuantity(quantityProduct);
        cart.setUser(user);
        shoppingCartService.save(cart);
        return "redirect:/product/productDetail/" + productId;
    }

    @PostMapping("/addProductFromProductDetailToWishList/{productId}")
    public String addProductToWishList(@PathVariable Long productId){
        User user = userLogin.userLogin();
        WishList wishList = new WishList();
        Product product = productService.findById(productId);
        wishList.setProduct(product);
        wishList.setUser(user);
        wishListService.save(wishList);
        return "redirect:/product/productDetail/" + productId;
    }
}
