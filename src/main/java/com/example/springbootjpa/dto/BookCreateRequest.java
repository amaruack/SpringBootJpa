package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.item.Book;
import com.example.springbootjpa.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest extends ItemCreateRequest {

    String author;
    String isbn;

    public Book toEntity() {
        return Book.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .author(this.author)
                .isbn(this.isbn)
                .build();
    }

}
