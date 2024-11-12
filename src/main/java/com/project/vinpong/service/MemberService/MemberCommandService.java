package com.project.vinpong.service.MemberService;

import com.project.vinpong.domain.Member;
import com.project.vinpong.jwt.JwtToken;
import com.project.vinpong.web.dto.MemberRequestDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberCommandService {

    Member joinMember (MemberRequestDTO.JoinDTO joinDTO);
    JwtToken signIn(String username, String password);

    Member getMyProfile(String username);

    Member kakaoOauthLogin(String accessCode, HttpServletResponse httpServletResponse);

    Member updateProfileImage(String username, MemberRequestDTO.updateMemberProfileImageDTO updateMemberProfileImageDTO);

    Member updateMemberPreferStyle(String username, MemberRequestDTO.memberStyleUpdateDTO memberStyleUpdateDTO);
}
