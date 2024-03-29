package com.example.springbootjpa.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ItemResponse {

    Long id;
    String name;
    Integer price;
    Integer stockQuantity;

}
