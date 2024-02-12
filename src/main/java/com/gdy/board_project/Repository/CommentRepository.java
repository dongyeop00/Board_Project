package com.gdy.board_project.Repository;

import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.CommentEntity;
import com.gdy.board_project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // select * from comment_table where board_id=? order by id desc;

    //select * from comment_table where board_id=? = findAllByBoardEntity
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);

    List<CommentEntity> findAllByBoardEntityAndMemberEntityOrderByIdDesc(BoardEntity boardEntity, MemberEntity memberEntity);

    List<CommentEntity> findAllByMemberEntityOrderByIdDesc(MemberEntity memberEntity);
}
