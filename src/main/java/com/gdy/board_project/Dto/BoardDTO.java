package com.gdy.board_project.Dto;

import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Enum.BoardCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;
    private Long memberId;
    private String category;


    public static BoardDTO toBoardDTO(BoardEntity boardEntity, String category){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContent());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setCategory(category);
        return boardDTO;
    }

    public static BoardDTO toBoardDTOList(BoardEntity boardEntity, Long memberId, String category){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContent());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        boardDTO.setMemberId(memberId);
        boardDTO.setCategory(category);
        return boardDTO;
    }

}
