package com.project.vinpong.web.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        String username;

        String id;

        String password;

        String phonenumber;

        String email;

        Integer gender; //1: MALE, 2: FEMALE

        String description;

        String profileImageUrl;

        String socialType;

        List<Long> preferStyles;
    }
}
