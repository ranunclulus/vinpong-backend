package com.project.vinpong.service.MemberService;

import com.project.vinpong.apiPayload.code.status.ErrorStatus;
import com.project.vinpong.apiPayload.exception.handler.MemberHandler;
import com.project.vinpong.apiPayload.exception.handler.StyleHandler;
import com.project.vinpong.converter.MemberConverter;
import com.project.vinpong.converter.MemberStyleConverter;
import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.mapping.MemberStyle;
import com.project.vinpong.jwt.JwtToken;
import com.project.vinpong.jwt.JwtTokenProvider;
import com.project.vinpong.repository.StyleRepository;
import com.project.vinpong.repository.MemberRepository;
import com.project.vinpong.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        if (memberRepository.existsByUsernamae(request.getUsername()))
            throw new MemberHandler(ErrorStatus.ALREADY_EXIST_MEMBERNAME);

        Member newMember = MemberConverter.toMember(request, passwordEncoder.encode(request.getPassword()));
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
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }
}
