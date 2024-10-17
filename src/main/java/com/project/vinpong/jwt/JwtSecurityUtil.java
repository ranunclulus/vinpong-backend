package com.project.vinpong.jwt;

import com.project.vinpong.apiPayload.code.status.ErrorStatus;
import com.project.vinpong.apiPayload.exception.handler.JwtHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtSecurityUtil {
    public static String getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new JwtHandler(ErrorStatus.ATHENTICATION_NOT_FOUND);
        }
        return authentication.getName();
    }
}
