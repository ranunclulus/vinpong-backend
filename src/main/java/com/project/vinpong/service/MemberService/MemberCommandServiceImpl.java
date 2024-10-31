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
import com.project.vinpong.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {
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

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String profileImageUrl = amazonS3Manager.uploadFile(
                amazonS3Manager.generateMemberKeyName(savedUuid), request.getProfileImage());

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
}
