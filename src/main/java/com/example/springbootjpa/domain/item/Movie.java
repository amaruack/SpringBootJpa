package com.example.springbootjpa.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@DiscriminatorColumn(name="Movie", discriminatorType=STRING, length=20)
public class Movie extends Item{

    String director;
    String actor;

}
