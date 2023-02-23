package com.example.springbootjpa.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@DiscriminatorColumn(name="Album", discriminatorType=STRING, length=20)
public class Album extends Item{

    String artist;
    String etc;

}
