package com.gdy.board_project.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    @CreationTimestamp // 만들어졌을 때 시간을 주는 정보
    @Column(updatable = false)//수정시 관여 x
    private LocalDateTime createdTime;

    @UpdateTimestamp //업데이트 됐을 때 시간을 주는 정보
    @Column(insertable = false) //입력시 관여 x
    private LocalDateTime updatedTime;
}
