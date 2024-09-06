package com.project.vinpong.converter;

import com.project.vinpong.domain.User;
import com.project.vinpong.domain.enums.Gender;
import com.project.vinpong.domain.enums.SocialType;
import com.project.vinpong.domain.enums.UserStatus;
import com.project.vinpong.web.dto.UserRequestDTO;
import com.project.vinpong.web.dto.UserResponseDTO;

import java.util.ArrayList;

public class UserConverter {
    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getUserId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDTO request) {
        SocialType socialType = null;

        Gender gender = null;
        switch (request.getGender()) {
            case "MALE":
                gender = Gender.MALE;
                break;
            case "FEMALE":
                gender = Gender.FEMALE;
                break;
        }
        return User.builder()
                .username(request.getUsername())
                .id(request.getId())
                .password(request.getPassword())
                .phonenumber(request.getPhonenumber())
                .email(request.getEmail())
                .gender(gender)
                .description(request.getDescription())
                .profileImageUrl(request.getProfileImageUrl())
                .socialType(socialType)
                .userStatus(UserStatus.ACTIVE)
                .itemList(new ArrayList<>())
                .userStyleList(new ArrayList<>())
                .build();

    }
}
