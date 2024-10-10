package com.project.vinpong.domain.mapping;

import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemStyle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemStyleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style")
    private Style style;

    public void setItem(Item item) {
        if (this.item != null)
            item.getItemStyleList().remove(item);
        this.item = item;
        item.getItemStyleList().add(this);
    }
}

