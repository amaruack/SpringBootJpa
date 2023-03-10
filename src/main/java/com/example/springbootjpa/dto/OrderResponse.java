package com.example.springbootjpa.dto;

import com.example.springbootjpa.config.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    Long orderId;
    LocalDateTime orderDate;
    OrderStatus orderStatus;

    MemberResponse member;
    DeliveryResponse delivery;
    List<OrderItemResponse> orderItems ;


}
