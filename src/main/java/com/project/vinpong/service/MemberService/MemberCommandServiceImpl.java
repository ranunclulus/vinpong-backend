package com.project.vinpong.service.MemberService;

import com.project.vinpong.apiPayload.code.status.ErrorStatus;
import com.project.vinpong.apiPayload.exception.handler.JwtHandler;
import com.project.vinpong.apiPayload.exception.handler.MemberHandler;
import com.project.vinpong.apiPayload.exception.handler.StyleHandler;
import com.project.vinpong.aws.s3.AmazonS3Manager;
import com.project.vinpong.converter.MemberConverter;
import com.project.vinpong.converter.MemberStyleConverter;
import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.Uuid;
import com.project.vinpong.domain.mapping.MemberStyle;
import com.project.vinpong.jwt.JwtToken;
import com.project.vinpong.jwt.JwtTokenProvider;
import com.project.vinpong.repository.StyleRepository;
import com.project.vinpong.repository.MemberRepository;
import com.project.vinpong.repository.UuidRepository;
import com.project.vinpong.util.KakaoUtil;
import com.project.vinpong.web.dto.KakaoDTO;
import com.project.vinpong.web.dto.MemberRequestDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberCommandServiceImpl implements MemberCommandService {
    private final KakaoUtil kakaoUtil;
    private final MemberRepository memberRepository;
    private final StyleRepository styleRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AmazonS3Manager amazonS3Manager;
    private final UuidRepository uuidRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        if (memberRepository.existsByUsernamae(request.getUsername()))
            throw new MemberHandler(ErrorStatus.ALREADY_EXIST_MEMBERNAME);
        if (memberRepository.existsByEmail(request.getEmail()))
            throw new MemberHandler(ErrorStatus.ALREADY_EXIST_EMAIL);
        String profileImageUrl = "";
        if (!request.getProfileImage().isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            Uuid savedUuid = uuidRepository.save(Uuid.builder()
                    .uuid(uuid).build());

            profileImageUrl = amazonS3Manager.uploadFile(
                    amazonS3Manager.generateMemberKeyName(savedUuid), request.getProfileImage());
        }

        Member newMember = MemberConverter.toMember(request, profileImageUrl, passwordEncoder.encode(request.getPassword()));
        List<Style> styleList = request.getPreferStyles().stream()
                .map(styleId -> {return styleRepository.findById(styleId).orElseThrow(() -> new StyleHandler(ErrorStatus.STYLE_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberStyle> memberStyleList = MemberStyleConverter.toMemberStyleList(styleList);
        memberStyleList.forEach(memberStyle -> memberStyle.setMember(newMember));
        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public JwtToken signIn(String username, String password) {
        if (!memberRepository.existsByUsernamae(username))
            throw new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND);
        try {
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication =
                    authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            return jwtTokenProvider.generateToken(authentication);
        } catch (Exception e) {
            throw new JwtHandler(ErrorStatus.PASSWORD_NOT_MATCH);
        }
    }

    @Override
    public Member getMyProfile(String username) {
        Optional<Member> member = memberRepository.findByUsernamae(username);
        if (member.isEmpty())
            throw new MemberHandler(ErrorStatus.ALREADY_EXIST_MEMBERNAME);

        return member.get();
    }

    @Override
    public Member kakaoOauthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);
        System.out.println("===================");
        System.out.println(oAuthToken.toString());
        System.out.println(kakaoProfile.getProperties().getNickname());
        return null;
    }
}
