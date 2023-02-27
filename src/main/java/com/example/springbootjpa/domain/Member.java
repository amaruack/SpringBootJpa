package com.example.springbootjpa.domain;

import com.example.springbootjpa.domain.valuetype.Address;
import com.example.springbootjpa.dto.MemberResponse;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_info")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders ;

    public MemberResponse toResponse() {
        return MemberResponse.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .build();
    }
}
