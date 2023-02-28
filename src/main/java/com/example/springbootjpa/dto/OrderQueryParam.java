package com.example.springbootjpa.dto;

import com.example.springbootjpa.config.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderQueryParam {
    String name;
    String userName;
    OrderStatus orderStatus;
}
