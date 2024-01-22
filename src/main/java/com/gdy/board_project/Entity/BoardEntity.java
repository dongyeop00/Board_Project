package com.gdy.board_project.Entity;

import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Member;

@Entity
@Setter
@Getter
@Table(name="board")
public class BoardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardTitle;

    @Column(length = 500)
    private String boardContent;

    @Column
    private int boardHits;

    // member:board=1:N 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;


    public static BoardEntity toEntity(BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    public static BoardEntity toSaveEntity(BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }
}
