package com.project.vinpong.domain.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 객체의 입장에서 공통의 매핑 정보가 계속 나올 때
@EntityListeners(AuditingEntityListener.class) // JPA Entity에 이벤트가 발생할 때 콜백을 처리하고 코드를 실행하는 방법
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;
}
