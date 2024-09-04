package com.project.vinpong.domain;

import com.project.vinpong.domain.common.BaseEntity;
import com.project.vinpong.domain.enums.ItemSize;
import com.project.vinpong.domain.enums.ItemState;
import com.project.vinpong.domain.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    private User seller;

    private String itemName;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    private ItemSize itemSize;

    private String description;

    @Enumerated(EnumType.STRING)
    private ItemState itemState;


}
