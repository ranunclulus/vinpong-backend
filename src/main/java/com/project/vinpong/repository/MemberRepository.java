package com.project.vinpong.repository;

import com.project.vinpong.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMembername(String memberName);
}
