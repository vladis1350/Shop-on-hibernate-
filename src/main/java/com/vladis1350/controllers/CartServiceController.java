package com.vladis1350.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.services.UserAccessService;
import com.vladis1350.auth.services.UserService;
import com.vladis1350.bean.embedded.UserShoppingCartId;
import com.vladis1350.bean.Product;
import com.vladis1350.bean.ShoppingCarts;
import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.constants.SuccessConstants;
import com.vladis1350.services.ProductService;
import com.vladis1350.services.ShoppingCartService;
import com.vladis1350.services.UserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class CartServiceController {

    @Autowired
    private UserShoppingCartService userShoppingCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private UserAccessService userAccessService;

    @PostMapping(value = Http.ADD_TO_CART + "/{id}")
    public String addProductToCart (
            @ModelAttribute("countOfGoods") Integer count,
            @PathVariable(name = "id") Long id) {
        User user = userService.getCurrentAuthenticationUser();
        if (cartService.findShoppingCartByIdUserBool(user)) {
            ShoppingCarts shoppingCart = ShoppingCarts.builder()
                    .user(user)
                    .build();
            cartService.saveShoppingCart(shoppingCart);
        }
        Product product = productService.getProductById(id);
        ShoppingCarts carts = cartService.findShoppingCartByUser(user);
        Long idShoppingCart = carts.getId();
        UserShoppingCartId shoppingCartId = UserShoppingCartId.builder()
                .shoppingCarts(carts)
                .product(product).build();
        if (userShoppingCartService.getByProductId(product.getId(), idShoppingCart) != null) {
            Integer quantityInBasket = userShoppingCartService.getQuantityProductsInUserShoppingCart(product.getId(), idShoppingCart);
            int totalQuantity = count+quantityInBasket;
            UserShoppingCart shoppingCart = UserShoppingCart.builder()
                    .pk(shoppingCartId)
                    .quantityProduct(totalQuantity)
                    .summOrder(product.getPrice().multiply(BigDecimal.valueOf(totalQuantity)))
                    .build();
            userShoppingCartService.update(shoppingCart);
        } else {
            UserShoppingCart userShoppingCart = UserShoppingCart.builder()
                    .pk(shoppingCartId)
                    .quantityProduct(count)
                    .summOrder(product.getPrice().multiply(BigDecimal.valueOf(count)))
                    .build();
            userShoppingCartService.save(userShoppingCart);
        }
        return Pages.REDIRECT + Pages.HOME;
    }

    @GetMapping(value = "/shopping_cart")
    public ModelAndView showUserShoppingCart() {
        ModelAndView mod = new ModelAndView("shopping_cart");
        User user = userService.getCurrentAuthenticationUser();
        Long idShoppingCart = cartService.findShoppingCartByUser(user).getId();
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject("userProductList", userShoppingCartService.findAllById(idShoppingCart));
        return mod;
    }

    @GetMapping(value = "/deleteUserProduct/{id_cart}/{id_product}")
    public ModelAndView deleteUserProduct(@PathVariable(name = "id_cart") Long idCart,
                                          @PathVariable(name = "id_product") Long idProduct) {
        ModelAndView mod = new ModelAndView();
        User user = userService.getCurrentAuthenticationUser();
        Long idShoppingCart = cartService.findShoppingCartByUser(user).getId();
        userShoppingCartService.remove(idCart, idProduct);
        mod.addObject("userProductList", userShoppingCartService.findAllById(idShoppingCart));
        mod.setViewName(Pages.REDIRECT + "shopping_cart");
        return mod;
    }
}