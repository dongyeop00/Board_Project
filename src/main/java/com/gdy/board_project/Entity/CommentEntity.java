package com.gdy.board_project.Entity;

import com.gdy.board_project.Dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length=20, nullable = false)
    private String commentCentents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity board, MemberEntity member) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentCentents(commentDTO.getCommentContents());
        commentEntity.setMemberEntity(member);
        commentEntity.setBoardEntity(board);
        return commentEntity;
    }
}
