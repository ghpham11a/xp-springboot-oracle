package com.gpham11a.xp_springboot_oracle.controllers;

import com.gpham11a.xp_springboot_oracle.entities.Order;
import com.gpham11a.xp_springboot_oracle.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(@Qualifier("primaryOrderService") OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> readOrders() {
        return orderService.readOrders();
    }
}
