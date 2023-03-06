package com.example.springbootjpa.dto;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.valuetype.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;
    private String city;
    private String street;
    private String zipCode;
//    private Address address;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .address( Address.builder()
                        .city(city)
                        .street(street)
                        .zipCode(zipCode)
                        .build())
                .build();
    }
}
