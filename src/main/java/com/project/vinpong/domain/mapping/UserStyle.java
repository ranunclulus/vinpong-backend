package com.project.vinpong.domain.mapping;

import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.User;
import com.project.vinpong.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserStyle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userStyleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    private Style style;

    public void setUser(User user) {
        if (this.user != null)
            user.getUserStyleList().remove(user);
        this.user = user;
        user.getUserStyleList().add(this);
    }
}
