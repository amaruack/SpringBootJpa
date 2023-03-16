package com.example.springbootjpa.dto.query;

import com.example.springbootjpa.config.OrderStatus;
import com.example.springbootjpa.domain.valuetype.Address;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderQuery {

    Long orderId;
    LocalDateTime orderDate;
    OrderStatus orderStatus;
    String userName;
    Address address;

    public OrderQuery(Long orderId, LocalDateTime orderDate, OrderStatus orderStatus, String userName, Address address) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.userName = userName;
        this.address = address;
    }
}
