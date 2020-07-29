package com.vladis1350.admin.controllers;

import com.vladis1350.bean.Order;
import com.vladis1350.bean.OrderStatuses;
import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.services.OrderService;
import com.vladis1350.services.UserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderManagementController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserShoppingCartService userShoppingCartService;

    @GetMapping(value = Http.ORDERS)
    public ModelAndView showAllOrders() {
        ModelAndView mod = new ModelAndView();
        mod.addObject("orderStatusList", OrderStatuses.values());
        mod.addObject("orderList", orderService.findAllOrder());
        mod.setViewName(Pages.ORDERS_PAGE);
        return mod;
    }

    @PostMapping(value = "/admin/changeOrderStatus")
    public ModelAndView changeOrderStatus(@RequestParam(name = "orderId", required = false) Long orderId,
                                          @RequestParam(name = "statusName", required = false) String statusName) {
        ModelAndView mod = new ModelAndView();
        orderService.editOrderStatus(OrderStatuses.valueOf(statusName), orderId);
        mod.addObject("orderStatusList", OrderStatuses.values());
        mod.addObject("orderList", orderService.findAllOrder());
        mod.setViewName(Pages.ORDERS_PAGE);
        return mod;
    }

    @GetMapping(value = Http.USER_ORDER + "/{orderId}")
    public ModelAndView showUserOrder(@PathVariable(name = "orderId") Long orderId) {
        ModelAndView mod = new ModelAndView(Pages.USER_ORDER_PAGE);
        Order order = orderService.findOrderById(orderId);
        Long idCart = order.getShoppingCarts().getId();
        List<UserShoppingCart> orderShoppingCarts = userShoppingCartService.findAllByIdShoppingCart(idCart);
        mod.addObject("buyer", order.getShoppingCarts().getUser());
        mod.addObject("orderCart", orderShoppingCarts);
        return mod;
    }

}
