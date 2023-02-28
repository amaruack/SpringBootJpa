package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.item.Item;

public class ItemCreateRequest {

    String name;
    Integer price;
    Integer stockQuantity;

    public Item toEntity() {
        return Item.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }

}
