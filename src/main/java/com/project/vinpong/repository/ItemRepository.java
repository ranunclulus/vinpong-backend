package com.project.vinpong.repository;

import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
