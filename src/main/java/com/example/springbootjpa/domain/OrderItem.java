package com.example.springbootjpa.domain;

import com.example.springbootjpa.domain.item.Item;
import com.example.springbootjpa.dto.OrderItemResponse;
import com.example.springbootjpa.dto.OrderResponse;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_item_info")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    Long id;

    @Column(name = "order_price")
    private Integer orderPrice;

    @Column(name = "count")
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    Item item;

    //=== 생성 메소드 ===//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();

        item.removeStock(count);
        return orderItem;
    }

    //=== 비즈니스 로직 ===//
    public void cancel(){
        getItem().addStock(this.count);
    }

    public int getTotalPrice(){
        return this.count * this.orderPrice;
    }


    public OrderItemResponse toResponse(){
        return OrderItemResponse.builder()
                .orderItemId(this.id)
                .orderPrice(this.orderPrice)
                .count(this.count)
                .item(this.item.toResponse())
                .build();
    }

}
