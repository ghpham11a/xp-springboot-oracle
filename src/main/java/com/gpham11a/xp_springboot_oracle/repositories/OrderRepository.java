package com.gpham11a.xp_springboot_oracle.repositories;

import com.gpham11a.xp_springboot_oracle.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    int createOrder(Order order);

    Optional<Order> getOrderById(String orderId);

    List<Order> getAllOrders();

    int updateOrder(Order order);

    // ---------------------------
    // 5. DELETE
    // ---------------------------
    int deleteOrder(String orderId);
}
