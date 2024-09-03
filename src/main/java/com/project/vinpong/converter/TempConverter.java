package com.project.vinpong.converter;

import com.project.vinpong.web.dto.TempResponse;

public class TempConverter {
    public static TempResponse.TempTestDTO toTempTestDTo() {
        return TempResponse.TempTestDTO.builder()
                .testString("This is Test")
                .build();
    }
}
