package com.project.vinpong.web.dto;

import lombok.Getter;

import java.util.List;

public class ItemRequestDTO {

    @Getter
    public static class JoinDTO {
        String itemName;
        Integer price;
        String itemState;
        String itemSize;
        String description;
        List<Long> itemCategoryList;
        List<Long> itemStyleList;
    }

    @Getter
    public static class searchDTO {
        String searchKeyword;
        List<Long> itemCategoryList;
        List<Long> itemStyleList;
    }


}
