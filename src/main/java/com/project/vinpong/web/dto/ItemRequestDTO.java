package com.project.vinpong.web.dto;

import lombok.Getter;

import java.util.List;

public class ItemRequestDTO {

    @Getter
    public static class JoinDTO {
        String seller;
        String itemName;
        Integer price;
        String itemStatus;
        String itemSize;
        String description;
        String itemState;
        List<Long> itemCategoryList;
        List<Long> itemStyleList;
    }
}
