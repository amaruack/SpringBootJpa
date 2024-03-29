package com.example.springbootjpa.domain.item;

import com.example.springbootjpa.dto.BookResponse;
import com.example.springbootjpa.dto.ItemResponse;
import lombok.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@DiscriminatorColumn(name="Book", discriminatorType=STRING, length=20)
public class Book extends Item{

    String author;
    String isbn;

    public BookResponse toResponse() {
        return BookResponse.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .author(this.author)
                .isbn(this.isbn)
                .build();
    }

}
