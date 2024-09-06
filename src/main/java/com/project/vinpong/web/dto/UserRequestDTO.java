package com.project.vinpong.web.dto;

import com.project.vinpong.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

import java.util.List;

public class UserRequestDTO {

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
