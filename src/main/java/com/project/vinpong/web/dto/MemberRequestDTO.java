package com.project.vinpong.web.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        String username;

        String password;

        String phonenumber;

        String email;

        Integer gender; //1: MALE, 2: FEMALE

        String description;

        String profileImageUrl;

        String socialType;

        List<Long> preferStyles;
    }

    @Getter
    @ToString
    public static class SignDTO {

        String username;

        String password;
    }
}
