package com.ra.controller.users;

import com.ra.model.entity.Product;
import com.ra.model.entity.ShoppingCart;
import com.ra.model.entity.User;
import com.ra.model.entity.dto.CheckOutInfor;
import com.ra.model.service.ProductService;
import com.ra.model.service.ShoppingCartService;
import com.ra.model.service.UserService;
import com.ra.security.UserDetail.UserLogin;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class ShoppingCartController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;
    private final UserLogin userLogin;


    @GetMapping("/cart")
    public String shoppingCart(Model model) {
        User user = userLogin.userLogin();
        List<ShoppingCart> carts = shoppingCartService.getAllByUser(user);
        model.addAttribute("carts", carts);
        double totalPrice = (double) 0;
        for (ShoppingCart cart:carts){
            totalPrice+=(cart.getProduct().getUnitPrice()*cart.getOrderQuantity());
        }
        model.addAttribute("totalPrice",totalPrice);
        return "home/shoppingCart/cart";
    }

    @PostMapping("/addProductToShoppingCart/{productId}")
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

    @GetMapping("/cart/checkOut")
    public String prepareCheckOut(Model model){
        User user = userLogin.userLogin();
        CheckOutInfor checkOutInfor = new CheckOutInfor();
        model.addAttribute("checkOutInfor",checkOutInfor);
        List<ShoppingCart> carts = shoppingCartService.getAllByUser(user);
        model.addAttribute("carts", carts);
        double totalPrice = (double) 0;
        for (ShoppingCart cart:carts){
            totalPrice+=(cart.getProduct().getUnitPrice()*cart.getOrderQuantity());
        }
        model.addAttribute("totalPrice",totalPrice);
        return "home/invoice/checkout";
    }

    @PostMapping("/checkOut")
    public String checkOut(@ModelAttribute("checkOutInfor")CheckOutInfor checkOutInfor){
        shoppingCartService.checkOut(checkOutInfor);
        return "redirect:/user/profile";
    }

}
