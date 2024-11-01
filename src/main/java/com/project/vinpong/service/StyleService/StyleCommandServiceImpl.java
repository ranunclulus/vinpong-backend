package com.project.vinpong.service.StyleService;

import com.project.vinpong.domain.Member;
import com.project.vinpong.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class StyleCommandServiceImpl implements StyleCommandService{
    private final StyleRepository styleRepository;

    @Override
    public List<String> findMemberStyleList(Member member) {
        List<String> memberStyleList = member.getMemberStyleList().stream()
                .map(memberStyle -> styleRepository.findById(memberStyle.getStyle().getStyleId()))
                .filter(Optional::isPresent)
                .map(optionalStyle -> optionalStyle.get().getStyle())
                .collect(Collectors.toList());
        return memberStyleList;
    }
}
