package com.project.vinpong.service.MemberService;

import com.project.vinpong.domain.Member;
import com.project.vinpong.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    public Member joinMember (MemberRequestDTO.JoinDTO request);
}