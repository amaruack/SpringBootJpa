package com.example.springbootjpa.domain;

public class MemberNameOnlyDto {

    String name;

    public MemberNameOnlyDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
