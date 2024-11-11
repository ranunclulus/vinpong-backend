package com.project.vinpong.web.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

public class KakaoDTO {

    @Getter
    public static class OAuthToken {
        private String access_token;
        private String refresh_token;
        private Integer refresh_token_expires_in;
        private Integer expires_in;
        private String scope;
        private String token_type;
    }

    @Getter
    public static class KakaoProfile {
        private Long id;
        private String connected_at;
        private Properties properties;
        private KakaoAccount kakao_account;

        @Getter
        public class Properties {
            private String nickname;
            private String profile_image;
            private String thumbnail_image;
        }

        @Getter
        public class KakaoAccount {
            private String email;
            private Boolean profile_nickname_needs_agreement;
            private Boolean profile_image_needs_agreement;
            private Profile profile;
            private Boolean name_needs_agreement;
            private Boolean email_needs_agreement;
            private Boolean has_email;
            private Boolean is_email_valid;
            private Boolean is_email_verified;

            @Getter
            public class Profile {
                private String nickname;
                private String thumbnail_image_url;
                private String profile_image_url;
                private Boolean is_default_image;
                private Boolean is_default_nickname;
            }
        }
    }
}
