package com.gpham11a.xp_springboot_oracle.services;

import com.gpham11a.xp_springboot_oracle.entities.Order;
import com.gpham11a.xp_springboot_oracle.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("primaryOrderService")
public class PrimaryOrderService implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public PrimaryOrderService(@Qualifier("orderJdbcRepository") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> readOrders() {
        return List.of(
                new Order("1", 1L, "", LocalDateTime.now()),
                new Order("2", 2L, "", LocalDateTime.now()),
                new Order("3", 3L, "", LocalDateTime.now()),
                new Order("4", 4L, "", LocalDateTime.now())
        );
    }
}
