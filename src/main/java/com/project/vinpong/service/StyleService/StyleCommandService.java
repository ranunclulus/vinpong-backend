package com.project.vinpong.service.StyleService;

import com.project.vinpong.domain.Member;

import java.util.List;

public interface StyleCommandService {
    List<String> findMemberStyleList(Member member);
}
