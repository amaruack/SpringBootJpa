package com.example.springbootjpa.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "group_info")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    List<Member> members = new ArrayList<>();

}
