package com.example.springbootjpa.domain;

import com.example.springbootjpa.config.DeliveryStatus;
import com.example.springbootjpa.domain.valuetype.Address;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery_info")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_id")
    Long id;

    @Embedded
    Address address;

    @Enumerated(EnumType.STRING)
    DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    Order order;

}
