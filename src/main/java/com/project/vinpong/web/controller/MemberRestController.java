package com.project.vinpong.web.controller;

import com.project.vinpong.apiPayload.ApiResponse;
import com.project.vinpong.converter.MemberConverter;
import com.project.vinpong.domain.Member;
import com.project.vinpong.jwt.JwtSecurityUtil;
import com.project.vinpong.jwt.JwtToken;
import com.project.vinpong.jwt.JwtTokenProvider;
import com.project.vinpong.service.MemberService.MemberCommandService;
import com.project.vinpong.service.StyleService.StyleCommandService;
import com.project.vinpong.web.dto.MemberRequestDTO;
import com.project.vinpong.web.dto.MemberResponseDTO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final JwtTokenProvider jwtTokenProvider;
    private final StyleCommandService styleCommandService;

    @PostMapping(value = "/signup", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@ModelAttribute @Valid MemberRequestDTO.JoinDTO joinDTO) {
        Member member = memberCommandService.joinMember(joinDTO);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/signin")
    public ApiResponse<MemberResponseDTO.SignInResultDTO> signIn(@RequestBody @Valid MemberRequestDTO.SignDTO signDTO) {

        JwtToken jwtToken = memberCommandService.signIn(signDTO.getUsername(), signDTO.getPassword());
        return ApiResponse.onSuccess(MemberConverter.toSignInResultDTO(jwtToken));
    }

    @GetMapping("/myprofile")
    public ApiResponse<MemberResponseDTO.MemberProfileResultDTO> getMemberProfile(HttpServletRequest request) {
        String username = extractUserName(request);
        Member member = memberCommandService.getMyProfile(username);
        List<String> styleList = styleCommandService.findMemberStyleList(member);
        return ApiResponse.onSuccess(MemberConverter.toMemberProfileResultDTO(member, styleList));
    }

    @GetMapping("/oauth2/kakao/signin")
    public ApiResponse<MemberResponseDTO.SignInResultDTO> kakaoOauth2SignIn(@RequestParam("code") String accessCode, HttpServletResponse httpServletResponse) {
        Member member = memberCommandService.kakaoOauthLogin(accessCode, httpServletResponse);
        JwtToken jwtToken = memberCommandService.signIn(member.getUsername(), member.getUsername());
        return ApiResponse.onSuccess(MemberConverter.toSignInResultDTO(jwtToken));
    }

    @PatchMapping(value = "/profileimage", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ApiResponse<MemberResponseDTO.MemberProfileResultDTO> updateProfileImage(@ModelAttribute @Valid MemberRequestDTO.updateMemberProfileImageDTO updateMemberProfileImageDTO,
                                                                                    HttpServletRequest request) {
        String username = extractUserName(request);
        Member member = memberCommandService.updateProfileImage(username, updateMemberProfileImageDTO);
        List<String> styleList = styleCommandService.findMemberStyleList(member);
        return ApiResponse.onSuccess(MemberConverter.toMemberProfileResultDTO(member, styleList));
    }

    @PatchMapping(value = "/memberstyle")
    public ApiResponse<MemberResponseDTO.MemberProfileResultDTO> updateMemberPreferStyle(@RequestBody @Valid MemberRequestDTO.memberStyleUpdateDTO memberStyleUpdateDTO,
                                                                                    HttpServletRequest request) {
        String username = extractUserName(request);
        Member member = memberCommandService.updateMemberPreferStyle(username, memberStyleUpdateDTO);
        List<String> styleList = styleCommandService.findMemberStyleList(member);
        return ApiResponse.onSuccess(MemberConverter.toMemberProfileResultDTO(member, styleList));
    }

    private String extractUserName(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Claims claims = jwtTokenProvider.parseClaims(token);
        return (String) claims.get("sub");
    }

}
