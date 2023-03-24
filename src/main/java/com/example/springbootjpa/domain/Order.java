package com.example.springbootjpa.domain;

import com.example.springbootjpa.config.DeliveryStatus;
import com.example.springbootjpa.config.OrderStatus;
import com.example.springbootjpa.dto.OrderResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
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
    List<OrderItem> orderItems ;

    /*==== 연관관계 편의 메서드  =====*/
    public void setMember(Member member) {
        this.member = member;
        member.orders.add(this);
    }

    public void addOrderItems(OrderItem orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // 생성 로직
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER)
                .build();
        for (OrderItem orderItem : orderItems) {
            order.addOrderItems(orderItem);
        }
        return order;
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem orderItem) {
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER)
                .build();
        order.addOrderItems(orderItem);
        return order;
    }

    public void cancel(){
        if (delivery.getStatus() == DeliveryStatus.COMPLETE){
            throw new IllegalStateException("이미 배송 완료");
        }
        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //전체 주문가격 조회
    public int getTotalPrice(){
        return orderItems.stream().mapToInt(orderItem -> orderItem.getTotalPrice()).sum();
    }

    public OrderResponse toResponse(){
        return OrderResponse.builder()
                .orderId(this.id)
                .orderDate(this.orderDate)
                .orderStatus(this.orderStatus)
                .member(this.member.toResponse())
                .delivery(this.delivery.toResponse())
                .orderItems(this.orderItems.stream().map(orderItem -> orderItem.toResponse()).collect(Collectors.toList()))
                .build();
    }

}
