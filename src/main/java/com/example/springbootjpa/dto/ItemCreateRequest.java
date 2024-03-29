package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequest {

    Long id;
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
