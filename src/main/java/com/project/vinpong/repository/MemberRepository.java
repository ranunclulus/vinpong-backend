package com.project.vinpong.repository;

import com.project.vinpong.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsernamae(String username);
    Optional<Member> findByUsernamae(String username);

    boolean existsByEmail(String email);
}
