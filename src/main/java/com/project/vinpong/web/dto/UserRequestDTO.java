package com.project.vinpong.web.dto;

import com.project.vinpong.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO {
        String username;

        String id;

        String password;

        String phonenumber;

        String email;

        String gender; //MALE, FEMALE

        String description;

        String profileImageUrl;
        String socialType;
    }
}
