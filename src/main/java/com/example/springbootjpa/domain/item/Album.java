package com.example.springbootjpa.domain.item;

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
//@DiscriminatorColumn(name="Album", discriminatorType=STRING, length=20)
public class Album extends Item{

    String artist;
    String etc;

}
