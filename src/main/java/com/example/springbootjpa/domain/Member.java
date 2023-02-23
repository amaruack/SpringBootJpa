package com.example.springbootjpa.domain;

import com.example.springbootjpa.domain.valuetype.Address;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Getter
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
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<Order> orders;

}
