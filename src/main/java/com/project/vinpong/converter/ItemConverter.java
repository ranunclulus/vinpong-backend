package com.project.vinpong.converter;

import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.enums.ItemSize;
import com.project.vinpong.domain.enums.ItemState;
import com.project.vinpong.domain.enums.ItemStatus;
import com.project.vinpong.web.dto.ItemRequestDTO;
import com.project.vinpong.web.dto.ItemResponseDTO;

public class ItemConverter {
    public static ItemResponseDTO.JoinResultDTO toJoinResultDTO(Item item) {
        return ItemResponseDTO.JoinResultDTO.builder()
                .itemId(item.getItemId())
                .createdAt(item.getCreatedAt())
                .build();
    }

    public static Item toItem(ItemRequestDTO.JoinDTO request) {

        ItemStatus itemStatus = ItemStatus.valueOf(request.getItemStatus());
        ItemSize itemSize = ItemSize.valueOf(request.getItemSize());
        ItemState itemState = ItemState.valueOf(request.getItemState());

        return Item.builder()
                .itemName(request.getItemName())
                .price(request.getPrice())
                .itemSize(itemSize)
                .itemState(itemState)
                .itemStatus(itemStatus)
                .description(request.getDescription())
                .build();

    }
}
