package com.project.vinpong.converter;

import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.enums.ItemSize;
import com.project.vinpong.domain.enums.ItemState;
import com.project.vinpong.domain.enums.ItemStatus;
import com.project.vinpong.web.dto.ItemRequestDTO;
import com.project.vinpong.web.dto.ItemResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemConverter {
    public static ItemResponseDTO.JoinResultDTO toJoinResultDTO(Item item) {
        return ItemResponseDTO.JoinResultDTO.builder()
                .itemId(item.getItemId())
                .createdAt(item.getCreatedAt())
                .build();
    }

    public static Item toItem(ItemRequestDTO.JoinDTO request, Member seller) {

        ItemSize itemSize = null;
        switch (request.getItemSize()) {
            case "XS":
                itemSize = ItemSize.XS;
                break;
            case "S":
                itemSize = ItemSize.S;
                break;
            case "M":
                itemSize = ItemSize.M;
                break;
            case "L":
                itemSize = ItemSize.L;
                break;
            case "XL":
                itemSize = ItemSize.XL;
                break;
            case "XXL":
                itemSize = ItemSize.XXL;
                break;
        }

        ItemState itemState = null;
        switch (request.getItemState()) {
            case "VERYBAD":
                itemState = ItemState.VERYBAD;
                break;
            case "BAD":
                itemState = ItemState.BAD;
                break;
            case "SOSO":
                itemState = ItemState.SOSO;
                break;
            case "GOOD":
                itemState = ItemState.GOOD;
                break;
            case "VERYGOOD":
                itemState = ItemState.VERYGOOD;
                break;
        }

        return Item.builder()
                .itemName(request.getItemName())
                .seller(seller)
                .price(request.getPrice())
                .itemSize(itemSize)
                .itemState(itemState)
                .itemStatus(ItemStatus.SELLING)
                .description(request.getDescription())
                .itemStyleList(new ArrayList<>())
                .itemCategoryList(new ArrayList<>())
                .itemImageList(new ArrayList<>())
                .build();

    }

    public static List<ItemResponseDTO.ReadResultDTO> toSearchItemsResultDTO(List<Item> items) {
        return items.stream()
                .map(item -> ItemResponseDTO.ReadResultDTO.builder()
                        .itemName(item.getItemName())
                        .seller(item.getSeller().getUsernamae())
                        .price(item.getPrice())
                        .itemStatus(item.getItemStatus())
                        .itemSize(item.getItemSize())
                        .description(item.getDescription())
                        .itemState(item.getItemState())
                        .firstItemImage(item.getItemImageList().isEmpty() ? null : item.getItemImageList().get(0).getItemImageUrl())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static ItemResponseDTO.ReadDetailResultDTO toItemtDTO(Item item) {
        List<String> itemImageUrls = item.getItemImageList().stream()
                .map(itemImage -> itemImage.getItemImageUrl()).collect(Collectors.toList());
        return ItemResponseDTO.ReadDetailResultDTO.builder()
                .seller(ItemResponseDTO.SellerDTO.builder()
                        .username(item.getSeller().getUsername())
                        .email(item.getSeller().getEmail())
                        .gender(item.getSeller().getGender().toString())
                        .description(item.getSeller().getDescription())
                        .profileImageUrl(item.getSeller().getProfileImageUrl())
                        .build())
                .price(item.getPrice())
                .itemState(item.getItemState())
                .itemStatus(item.getItemStatus())
                .itemSize(item.getItemSize())
                .itemName(item.getItemName())
                .description(item.getDescription())
                .itemImageList(itemImageUrls)
                .build();

    }
}
