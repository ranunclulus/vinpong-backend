package com.project.vinpong.repository;

import com.project.vinpong.domain.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, Long> {
    Optional<Style> findByStyle(String style);
}
