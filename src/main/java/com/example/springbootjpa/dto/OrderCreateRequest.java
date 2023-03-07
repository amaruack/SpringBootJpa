package com.example.springbootjpa.dto;

import com.example.springbootjpa.config.OrderStatus;
import com.example.springbootjpa.domain.Delivery;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.domain.OrderItem;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderCreateRequest {

    Long memberId;
    Long itemId;
    Integer count;

    List<OrderItemCreateRequest> orderItems ;

//    public Order toEntity(){
//        return Order.createOrder(this.member, this.delivery,
//                orderItems.stream().map(orderItem -> orderItem.toEntity()));
//    }

}
