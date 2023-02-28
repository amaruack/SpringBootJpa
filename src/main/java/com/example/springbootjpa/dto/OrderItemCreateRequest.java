package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.Delivery;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.domain.OrderItem;
import com.example.springbootjpa.domain.item.Item;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
public class OrderItemCreateRequest {

    private Long itemId;
    private Integer orderPrice;
    private Integer count;

    public OrderItem toEntity(Item item){
        return OrderItem.createOrderItem(item, this.orderPrice, this.count);
    }

}
