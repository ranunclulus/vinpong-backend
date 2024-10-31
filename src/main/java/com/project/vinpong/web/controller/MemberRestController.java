package com.project.vinpong.web.controller;

import com.project.vinpong.apiPayload.ApiResponse;
import com.project.vinpong.converter.MemberConverter;
import com.project.vinpong.domain.Member;
import com.project.vinpong.jwt.JwtSecurityUtil;
import com.project.vinpong.jwt.JwtToken;
import com.project.vinpong.service.MemberService.MemberCommandService;
import com.project.vinpong.web.dto.MemberRequestDTO;
import com.project.vinpong.web.dto.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;

    @PostMapping(value = "/signup/general", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@ModelAttribute @Valid MemberRequestDTO.JoinDTO request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/signin/general")
    public ApiResponse<MemberResponseDTO.SignInResultDTO> signIn(@RequestBody @Valid MemberRequestDTO.SignDTO request) {

        JwtToken jwtToken = memberCommandService.signIn(request.getUsername(), request.getPassword());
        return ApiResponse.onSuccess(MemberConverter.toSignInResultDTO(jwtToken));
    }

}
