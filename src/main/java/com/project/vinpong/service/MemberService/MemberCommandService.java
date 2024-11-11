package com.project.vinpong.service.MemberService;

import com.project.vinpong.domain.Member;
import com.project.vinpong.jwt.JwtToken;
import com.project.vinpong.web.dto.MemberRequestDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberCommandService {

    public Member joinMember (MemberRequestDTO.JoinDTO request);
    public JwtToken signIn(String username, String password);

    Member getMyProfile(String username);

    Member kakaoOauthLogin(String accessCode, HttpServletResponse httpServletResponse);

    Member kakaoOauthJoin(String accessCode, HttpServletResponse httpServletResponse);
}
