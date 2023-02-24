package com.example.springbootjpa.domain;

import com.example.springbootjpa.config.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    Delivery delivery;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<OrderItem> orderItems = new ArrayList<>();

    /*==== 연관관계 편의 메서드  =====*/
    public void setMember(Member member) {
        this.member = member;
        member.orders.add(this);
    }

    public void addOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
