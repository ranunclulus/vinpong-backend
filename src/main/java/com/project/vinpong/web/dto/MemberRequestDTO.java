package com.project.vinpong.web.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDTO {
        String username;

        String password;

        String phonenumber;

        String email;

        Integer gender; //1: MALE, 2: FEMALE

        String description;

        MultipartFile profileImage;

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
