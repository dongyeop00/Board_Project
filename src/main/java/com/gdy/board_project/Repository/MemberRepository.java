package com.gdy.board_project.Repository;

import com.gdy.board_project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
