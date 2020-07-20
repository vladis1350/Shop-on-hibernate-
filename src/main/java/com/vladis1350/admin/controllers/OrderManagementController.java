package com.vladis1350.admin.controllers;

import com.vladis1350.bean.Order;
import com.vladis1350.bean.OrderStatuses;
import com.vladis1350.bean.StatusOrder;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderManagementController {

    @Autowired
    private OrderService orderService;

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

}
