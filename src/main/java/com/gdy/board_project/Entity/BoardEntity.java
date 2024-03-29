package com.gdy.board_project.Entity;

import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Enum.BoardCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private BoardCategory category; // 카테고리

    // member:board=1:N 관계
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    public static BoardEntity toEntity(BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    //insert
    public static BoardEntity toSaveEntity(BoardCategory category, BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContents());
        boardEntity.setCategory(category);
        boardEntity.setBoardHits(0);
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    //update
    public static BoardEntity toUpdateEntity(BoardCategory category, BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContents());
        boardEntity.setCategory(category);
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    public String getMemberName(){ //member_id에 접속해 해당 id의 memberName 알아내기!
        return memberEntity.getMemberName();
    }

    public Long getMemberID(){
        return memberEntity.getId();
    }
}
