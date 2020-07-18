package com.vladis1350.admin.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.bean.Order;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderManagementController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = Http.ORDERS)
    public ModelAndView showAllOrders() {
        ModelAndView mod = new ModelAndView();
//        List<Order> orderList = orderService.findAllOrder();
//        User user = orderList.get(1).getShoppingCarts().getUser();
        mod.addObject("orderList", orderService.findAllOrder());
        mod.setViewName(Pages.ORDERS_PAGE);
        return mod;
    }

}
