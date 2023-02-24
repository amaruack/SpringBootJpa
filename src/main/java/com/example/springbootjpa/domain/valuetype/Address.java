package com.example.springbootjpa.domain.valuetype;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private String zipCode;

    protected Address() {}

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
