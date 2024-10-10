package com.project.vinpong.web.dto;

import lombok.Getter;

import java.util.List;

public class ItemRequestDTO {

    @Getter
    public static class JoinDTO {
        String seller;
        String itemName;
        Integer price;
        String itemState;
        String itemSize;
        String description;
        List<Long> itemCategoryList;
        List<Long> itemStyleList;
    }
}
