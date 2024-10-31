package com.project.vinpong.web.dto;

import com.project.vinpong.domain.ItemImage;
import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.enums.ItemSize;
import com.project.vinpong.domain.enums.ItemState;
import com.project.vinpong.domain.enums.ItemStatus;
import com.project.vinpong.domain.mapping.ItemCategory;
import com.project.vinpong.domain.mapping.ItemStyle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ItemResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long itemId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadResultDTO {
        String seller;
        String itemName;
        Integer price;
        ItemStatus itemStatus;
        ItemSize itemSize;
        String description;
        ItemState itemState;
        String firstItemImage;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadDetailResultDTO {
        SellerDTO seller;
        String itemName;
        Integer price;
        ItemStatus itemStatus;
        ItemSize itemSize;
        String description;
        ItemState itemState;
        List<String> itemImageList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SellerDTO {
        String username;
        String email;
        String gender;
        String description;
        String profileImageUrl;
    }



}
