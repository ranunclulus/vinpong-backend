package com.project.vinpong.config;

import com.project.vinpong.jwt.JwtAuthenticationFilter;
import com.project.vinpong.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authHttp -> authHttp // HTTP 요청 허가 관련 설정을 하고 싶다.
                                // requestMatchers == 어떤 URL로 오는 요청에 대하여 설정하는지
                                // permitAll() == 누가 요청해도 허가한다.
                                .requestMatchers(
                                        "/members/sign-in",
                                        "v1/users/signup/general",
                                        "v1/users/signin/general"
                                )
                                .permitAll()
                                .requestMatchers(
                                        "/members/test"
                                )
                                .hasRole("USER")// 인증이 되지 않은 사용자만 허가
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
