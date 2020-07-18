package com.vladis1350.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.services.UserAccessService;
import com.vladis1350.auth.services.UserService;
import com.vladis1350.bean.Order;
import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.constants.Pages;
import com.vladis1350.constants.SuccessConstants;
import com.vladis1350.services.OrderService;
import com.vladis1350.services.UserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserShoppingCartService userShoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAccessService userAccessService;

    @PostMapping("/checkoutOrder")
    public ModelAndView saveNewOrder() {
        ModelAndView mod = new ModelAndView();
        User user = userService.getCurrentAuthenticationUser();
        Order order = Order.builder()
                .user(user)
                .date(new Date().toString())
                .build();
        orderService.saveNewOrder(order, Optional.empty());
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject("orderMessage", "Заказ отправлен на обработку, с вами свяжется администратор для уточнения заказа.");
        mod.setViewName(Pages.REDIRECT + "shopping_cart");
        return mod;
    }
}
