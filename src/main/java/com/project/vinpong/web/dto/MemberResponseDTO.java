package com.project.vinpong.web.dto;

import com.project.vinpong.jwt.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long memberId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInResultDTO {
        JwtToken jwtToken;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberProfileResultDTO {
        String username;
        String email;
        String gender;
        String profileImageUrl;
        String memberStatus;
        Integer itemCount;
        List<String> memberStyleList;
    }

}
