package com.project.vinpong.converter;

import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.mapping.MemberStyle;

import java.util.List;
import java.util.stream.Collectors;

public class MemberStyleConverter {

    public static List<MemberStyle> toMemberStyleList(List<Style> styleList) {
        return styleList.stream()
                .map(style -> MemberStyle.builder()
                        .style(style)
                        .build()
                ).collect(Collectors.toList());
    }
}
