package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.Delivery;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {

    Member member;
    Delivery delivery;
    List<OrderItem> orderItems ;

}
