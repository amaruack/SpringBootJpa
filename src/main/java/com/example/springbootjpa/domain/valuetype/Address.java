package com.example.springbootjpa.domain.valuetype;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private String zipCode;

}
