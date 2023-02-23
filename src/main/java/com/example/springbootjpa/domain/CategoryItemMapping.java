package com.example.springbootjpa.domain;

import com.example.springbootjpa.domain.id.CategoryItemMappingId;
import com.example.springbootjpa.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category_item_mapp")
@IdClass(value = CategoryItemMappingId.class)
public class CategoryItemMapping {

    @Id
    @Column(name = "category_id")
    Long categoryId;
    @Id
    @Column(name = "item_id")
    Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", insertable = false, updatable = false)
    Item item;

}
