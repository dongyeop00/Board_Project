package com.gdy.board_project.Repository;

import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    //List<BoardEntity> findByMemberUserid(Long userid);
}
