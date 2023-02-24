package com.example.springbootjpa.domain;

import com.example.springbootjpa.domain.item.Item;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
