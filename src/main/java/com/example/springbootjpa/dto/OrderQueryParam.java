package com.example.springbootjpa.dto;

import com.example.springbootjpa.config.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryParam {
    String name;
    String userName;
    OrderStatus orderStatus;
}
