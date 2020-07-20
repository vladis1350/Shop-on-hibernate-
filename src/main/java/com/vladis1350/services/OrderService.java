package com.vladis1350.services;

import com.vladis1350.bean.Order;
import com.vladis1350.bean.OrderStatuses;
import com.vladis1350.bean.StatusOrder;
import com.vladis1350.repositories.OrderRepository;
import com.vladis1350.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    public Order saveNewOrder(Order order, Optional<OrderStatuses> orderStatusesOp) {
        OrderStatuses newOrderStatuses = orderStatusesOp.orElseGet(() -> OrderStatuses.ACCEPTED_FOR_PROCESSING);

        StatusOrder statusOrder = statusRepository.findByStatusName(newOrderStatuses);
        if (statusOrder == null) {
            StatusOrder newStatusOrder = StatusOrder.builder()
                    .statusName(newOrderStatuses)
                    .build();
            statusOrder = statusRepository.save(newStatusOrder);
        }
        order.setStatusOrder(statusOrder);
        return orderRepository.save(order);
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }

    public void editOrderStatus(OrderStatuses statusName, Long orderId) {
        StatusOrder statusOrder = statusRepository.findByStatusName(statusName);
        if (statusOrder == null) {
            StatusOrder orderS = StatusOrder.builder()
                    .statusName(statusName)
                    .build();
            statusOrder = statusRepository.save(orderS);
        }
        Optional<Order> orderOp = orderRepository.findById(orderId);
        if (orderOp.isPresent()) {
            Order order = orderOp.get();
            order.setStatusOrder(statusOrder);
            orderRepository.save(order);
        }
    }
}
