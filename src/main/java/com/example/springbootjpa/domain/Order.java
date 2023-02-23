package com.example.springbootjpa.domain;

import com.example.springbootjpa.config.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_info")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    Long id;

    @Column(name = "order_date")
    LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    Delivery delivery;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    List<OrderItem> orderItems;

}
