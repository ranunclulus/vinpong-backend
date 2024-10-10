package com.project.vinpong.converter;

import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.mapping.ItemStyle;
import com.project.vinpong.domain.mapping.MemberStyle;

import java.util.List;
import java.util.stream.Collectors;

public class ItemStyleConverter {
    public static List<ItemStyle> toItemStyleList(List<Style> styleList) {
        return styleList.stream()
                .map(style -> ItemStyle.builder()
                        .style(style)
                        .build()
                ).collect(Collectors.toList());
    }
}
