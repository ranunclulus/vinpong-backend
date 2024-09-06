package com.project.vinpong.domain;

import com.project.vinpong.domain.common.BaseEntity;
import com.project.vinpong.domain.enums.Gender;
import com.project.vinpong.domain.enums.SocialType;
import com.project.vinpong.domain.enums.UserStatus;
import com.project.vinpong.domain.mapping.UserStyle;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String id;

    @Column(nullable = false, length = 20)
    private String password;

    private String phonenumber;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String description;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus userStatus;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Item> itemList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserStyle> userStyleList;
}
