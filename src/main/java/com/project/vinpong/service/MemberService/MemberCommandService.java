package com.project.vinpong.service.MemberService;

import com.project.vinpong.domain.Member;
import com.project.vinpong.jwt.JwtToken;
import com.project.vinpong.web.dto.MemberRequestDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberCommandService {

    public Member joinMember (MemberRequestDTO.JoinDTO joinDTO);
    public JwtToken signIn(String username, String password);

    Member getMyProfile(String username);

    Member kakaoOauthLogin(String accessCode, HttpServletResponse httpServletResponse);

    Member updateProfileImage(String username, MemberRequestDTO.updateMemberProfileImageDTO updateMemberProfileImageDTO);
}
