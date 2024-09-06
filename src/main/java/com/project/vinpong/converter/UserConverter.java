package com.project.vinpong.converter;

import com.project.vinpong.domain.User;
import com.project.vinpong.web.dto.UserResponseDTO;

public class UserConverter {
    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getUserId())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
