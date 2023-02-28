package com.example.springbootjpa.domain.item;

import com.example.springbootjpa.domain.CategoryItemMapping;
import com.example.springbootjpa.dto.ItemResponse;
import com.example.springbootjpa.dto.MemberResponse;
import com.example.springbootjpa.exception.NotEnoughStockException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_info")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 해당 애노테이션은 DTYPE 을 넣어준다. 하위 객체의 타입을 넣어준다.
@DiscriminatorColumn(name = "DTYPE")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    Integer price;

    @Column(name = "stock_quantity")
    Integer stockQuantity;

    // 해당 연관관계는 필요가 없다
//    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
//    List<OrderItem> orderItems;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CategoryItemMapping> categoryItemMappings;

    public Integer addStock(int stockQuantity) {
        return this.stockQuantity += stockQuantity;
    }

    public Integer removeStock(int stockQuantity) {

        int rest = this.stockQuantity - stockQuantity;
        if (rest < 0) {
            throw new NotEnoughStockException("stock is small");
        }
        this.stockQuantity = rest;
        return rest;

    }

    public ItemResponse toResponse() {
        return ItemResponse.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }

}
