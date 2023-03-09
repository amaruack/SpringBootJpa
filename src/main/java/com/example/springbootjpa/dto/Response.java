package com.example.springbootjpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    String responseCode;
    String responseMessage;
    Object data;
}
