package com.project.vinpong.converter;

import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.enums.Gender;
import com.project.vinpong.domain.enums.SocialType;
import com.project.vinpong.domain.enums.MemberStatus;
import com.project.vinpong.web.dto.MemberRequestDTO;
import com.project.vinpong.web.dto.MemberResponseDTO;

import java.util.ArrayList;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getMemberId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request) {
        SocialType socialType = null;

        Gender gender = null;
        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }
        return Member.builder()
                .membername(request.getMembername())
                .id(request.getId())
                .password(request.getPassword())
                .phonenumber(request.getPhonenumber())
                .email(request.getEmail())
                .gender(gender)
                .description(request.getDescription())
                .profileImageUrl(request.getProfileImageUrl())
                .socialType(socialType)
                .memberStatus(MemberStatus.ACTIVE)
                .itemList(new ArrayList<>())
                .memberStyleList(new ArrayList<>())
                .build();

    }
}
