package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.domain.valuetype.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {

    private String name;

    private Address address;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .address(this.address)
                .build();
    }
}
