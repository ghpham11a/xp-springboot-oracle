package com.gpham11a.xp_springboot_oracle.services;

import com.gpham11a.xp_springboot_oracle.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> readOrders();
}
