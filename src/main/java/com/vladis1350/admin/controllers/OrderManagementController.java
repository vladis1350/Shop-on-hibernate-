package com.vladis1350.admin.controllers;

import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderManagementController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = Http.ORDERS)
    public ModelAndView showAllOrders() {
        ModelAndView mod = new ModelAndView();
        mod.addObject("orderList", orderService.findAllOrder());
        mod.setViewName(Pages.ORDERS_PAGE);
        return mod;
    }

}
