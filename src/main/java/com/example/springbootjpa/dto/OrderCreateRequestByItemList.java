package com.example.springbootjpa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderCreateRequestByItemList {

    Long memberId;

    List<OrderItemCreateRequest> orderItems ;

//    public Order toEntity(){
//        return Order.createOrder(this.member, this.delivery,
//                orderItems.stream().map(orderItem -> orderItem.toEntity()));
//    }

}
