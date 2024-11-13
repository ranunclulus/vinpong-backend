package com.project.vinpong.repository;

import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.mapping.MemberStyle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStyleRepository extends JpaRepository<MemberStyle, Long> {
    void deleteAllByMember(Member member);
}

