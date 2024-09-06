package com.project.vinpong.converter;

import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.mapping.UserStyle;

import java.util.List;
import java.util.stream.Collectors;

public class UserStyleConverter {

    public static List<UserStyle> toUserStyleList(List<Style> styleList) {
        return styleList.stream()
                .map(style -> UserStyle.builder()
                        .style(style)
                        .build()
                ).collect(Collectors.toList());
    }
}
