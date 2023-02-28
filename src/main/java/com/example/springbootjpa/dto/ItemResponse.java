package com.example.springbootjpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {

    Long id;
    String name;
    Integer price;
    Integer stockQuantity;

}
