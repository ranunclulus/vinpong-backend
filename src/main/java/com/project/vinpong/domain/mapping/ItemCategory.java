package com.project.vinpong.domain.mapping;

import com.project.vinpong.domain.Category;
import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setItem(Item item) {
        if (this.item != null) {
            item.getItemCategoryList().remove(item);
        }
        this.item = item;
        item.getItemCategoryList().add(this);
    }
}
