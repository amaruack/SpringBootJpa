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
//@DiscriminatorColumn(name="Album", discriminatorType=STRING, length=20)
public class Album extends Item{

    String artist;
    String etc;

}
