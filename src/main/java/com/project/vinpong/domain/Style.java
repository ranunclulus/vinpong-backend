package com.project.vinpong.domain;

import com.project.vinpong.domain.common.BaseEntity;
import com.project.vinpong.domain.mapping.ItemStyle;
import com.project.vinpong.domain.mapping.UserStyle;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Style extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long styleId;

    @Column(nullable = false)
    private String style;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL)
    private List<ItemStyle> itemStyleList;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL)
    private List<UserStyle> userStyleList;
}
