package com.example.springbootjpa.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BookResponse extends ItemResponse {

    String author;
    String isbn;

}
