package com.ra.controller.users;

import com.ra.model.entity.Product;
import com.ra.model.entity.ShoppingCart;
import com.ra.model.entity.User;
import com.ra.model.service.ProductService;
import com.ra.model.service.ShoppingCartService;
import com.ra.model.service.UserService;
import com.ra.security.UserDetail.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ShoppingCartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserLogin userLogin;

    @GetMapping("/cart")
    public String shoppingCart(Model model) {
        User user = userLogin.userLogin();
        List<ShoppingCart> carts = shoppingCartService.getAllByUser(user);
        model.addAttribute("carts", carts);
        return "home/shoppingCart/cart";
    }

    @PostMapping("/addProductToShoppingCart/{productId}")
    public String addProductToShoppingCart(@PathVariable Long productId) {
        User user = userLogin.userLogin();
        ShoppingCart cart = new ShoppingCart();
        cart.setProduct(productService.findById(productId));
        cart.setOrderQuantity(1);
        cart.setUser(user);
        shoppingCartService.save(cart);
        return "redirect:/shop";
    }

    @GetMapping("/cart/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        User user = userLogin.userLogin();
        shoppingCartService.deleteByUserAndId(user, id);
        return "redirect:/user/cart";
    }

    @PostMapping("/cart/updateQuantity")
    public String updateProductInShoppingCart(@RequestParam(name = "idCart") Long idCart,
                                              @RequestParam(name = "quantityProduct") int quantityProduct) {
        shoppingCartService.updateOrderQuantity(idCart,quantityProduct);
        return "redirect:/user/cart";
    }

}
