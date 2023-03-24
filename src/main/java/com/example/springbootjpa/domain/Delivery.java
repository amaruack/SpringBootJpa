package com.example.springbootjpa.domain;

import com.example.springbootjpa.config.DeliveryStatus;
import com.example.springbootjpa.domain.valuetype.Address;
import com.example.springbootjpa.dto.DeliveryResponse;
import com.example.springbootjpa.dto.OrderResponse;
import lombok.*;

import javax.persistence.*;
import java.util.stream.Collectors;

@Getter
@Setter
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

    public DeliveryResponse toResponse(){
        return DeliveryResponse.builder()
                .deliveryId(this.id)
                .address(this.address)
                .status(this.status)
                .build();
    }
}
