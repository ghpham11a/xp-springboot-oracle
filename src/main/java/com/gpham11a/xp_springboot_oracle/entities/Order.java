package com.gpham11a.xp_springboot_oracle.entities;

import java.time.LocalDateTime;

public class Order {

    private String orderId;

    private Long orderAmount;

    private String description;

    private LocalDateTime orderDate;

    public Order(String orderId, Long orderAmount, String description, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.orderAmount = orderAmount;
        this.description = description;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
