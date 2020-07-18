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
}
