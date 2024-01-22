package com.gdy.board_project.Repository;

import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Enum.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findAllByCategory(BoardCategory category);
    List<BoardEntity> findByCategory(BoardCategory category);

}
