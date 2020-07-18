package com.vladis1350.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.services.UserAccessService;
import com.vladis1350.auth.services.UserService;
import com.vladis1350.bean.Order;
import com.vladis1350.bean.ShoppingCarts;
import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.constants.Pages;
import com.vladis1350.constants.SuccessConstants;
import com.vladis1350.services.OrderService;
import com.vladis1350.services.ShoppingCartService;
import com.vladis1350.services.UserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAccessService userAccessService;

    @PostMapping("/checkoutOrder")
    public ModelAndView saveNewOrder() {
        ModelAndView mod = new ModelAndView();
        User user = userService.getCurrentAuthenticationUser();
        ShoppingCarts shoppingCart = shoppingCartService.findShoppingCartByUserAndIsActiveTrue(user);
        DateFormat currentDate = new SimpleDateFormat("dd.MM.yyy HH:mm");
        Order order = Order.builder()
                .shoppingCarts(shoppingCart)
                .date(currentDate.format(new Date()))
                .build();
        if (orderService.saveNewOrder(order, Optional.empty()) != null) {
            shoppingCart.setIsActive(false);
            shoppingCartService.saveShoppingCart(shoppingCart);
        }
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject("orderMessage", "Заказ отправлен на обработку, с вами свяжется администратор для уточнения заказа.");
        mod.setViewName(Pages.REDIRECT + "shopping_cart");
        return mod;
    }
}
