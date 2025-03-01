package com.gpham11a.xp_springboot_oracle.repositories;

import com.gpham11a.xp_springboot_oracle.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("orderJdbcRepository")
public class OrderJdbcRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createOrder(Order order) {
        String sql = "INSERT INTO ORDERS (ORDER_ID, ORDER_AMOUNT, DESCRIPTION, ORDER_DATE) "
                + "VALUES (?, ?, ?, ?)";

        // Convert LocalDateTime to Timestamp for insertion (if needed)
        Timestamp orderDateTimestamp = (order.getOrderDate() != null)
                ? Timestamp.valueOf(order.getOrderDate())
                : null;

        return jdbcTemplate.update(
                sql,
                order.getOrderId(),
                order.getOrderAmount(),
                order.getDescription(),
                orderDateTimestamp
        );
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        String sql = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
        List<Order> results = jdbcTemplate.query(sql, new Object[] { orderId }, new OrderRowMapper());

        // If no results found, return Optional.empty()
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM ORDERS";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    @Override
    public int updateOrder(Order order) {
        String sql = "UPDATE ORDERS "
                + "SET ORDER_AMOUNT = ?, DESCRIPTION = ?, ORDER_DATE = ? "
                + "WHERE ORDER_ID = ?";

        Timestamp orderDateTimestamp = (order.getOrderDate() != null)
                ? Timestamp.valueOf(order.getOrderDate())
                : null;

        return jdbcTemplate.update(
                sql,
                order.getOrderAmount(),
                order.getDescription(),
                orderDateTimestamp,
                order.getOrderId()
        );
    }

    // ---------------------------
    // 5. DELETE
    // ---------------------------
    @Override
    public int deleteOrder(String orderId) {
        String sql = "DELETE FROM ORDERS WHERE ORDER_ID = ?";
        return jdbcTemplate.update(sql, orderId);
    }

    private static class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            String orderId = rs.getString("ORDER_ID");
            Long orderAmount = rs.getLong("ORDER_AMOUNT");
            String description = rs.getString("DESCRIPTION");

            Timestamp timestamp = rs.getTimestamp("ORDER_DATE");
            LocalDateTime orderDate = (timestamp != null) ? timestamp.toLocalDateTime() : null;

            return new Order(orderId, orderAmount, description, orderDate);
        }
    }
}
