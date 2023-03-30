package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.valuetype.Address;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;
    private Integer age;
    private Address address;

    @QueryProjection
    public MemberResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
