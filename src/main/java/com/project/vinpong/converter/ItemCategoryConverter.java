package com.project.vinpong.converter;

import com.project.vinpong.domain.Category;
import com.project.vinpong.domain.mapping.ItemCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCategoryConverter {
    public static List<ItemCategory> toItemCategoryList(List<Category> categoryList) {
        return categoryList.stream()
                .map(category -> ItemCategory.builder()
                        .category(category)
                        .build()
                ).collect(Collectors.toList());
    }
}
