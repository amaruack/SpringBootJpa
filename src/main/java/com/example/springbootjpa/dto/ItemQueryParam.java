package com.example.springbootjpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemQueryParam {
    String name;
}
