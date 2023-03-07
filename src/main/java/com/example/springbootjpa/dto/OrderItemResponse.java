package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
public class OrderItemResponse {

    Long orderItemId;
    Integer orderPrice;
    Integer count;
    ItemResponse item;

}
