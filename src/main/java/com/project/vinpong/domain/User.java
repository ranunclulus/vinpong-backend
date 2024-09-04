package com.project.vinpong.domain;

import com.project.vinpong.domain.common.BaseEntity;
import com.project.vinpong.domain.enums.Gender;
import com.project.vinpong.domain.enums.SocialType;
import com.project.vinpong.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String phonenumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String description;

    private String profileImgUrl;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}
