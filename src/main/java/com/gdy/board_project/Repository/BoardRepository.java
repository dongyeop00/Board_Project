package com.gdy.board_project.Repository;

import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Enum.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findAllByCategory(BoardCategory category);
    List<BoardEntity> findByCategory(BoardCategory category);

    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying //@Query 사용할 때 필수로 붙이는 어노테이션
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id); //id 값이 id123으로 바뀌면 쿼리문 id는 id123으로 바뀜

}
