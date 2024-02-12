package com.gdy.board_project.Dto;

import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.CommentEntity;
import com.gdy.board_project.Entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private Long memberId;
    private Long boardId;
    private String memberName;
    private String commentContents;
    private LocalDateTime commentCreatedTime;
    private String boardTitle;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, BoardEntity boardEntity, MemberEntity memberEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentContents(commentEntity.getCommentCentents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setMemberName(memberEntity.getMemberName());
        commentDTO.setMemberId(memberEntity.getId());
        commentDTO.setBoardId(boardEntity.getId());
        return commentDTO;
    }

    public static CommentDTO toCommentDTOList(CommentEntity commentEntity, Long boardId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentContents(commentEntity.getCommentCentents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setBoardId(boardId);
        commentDTO.setMemberName(commentEntity.getMemberEntity().getMemberName());
        commentDTO.setMemberId(commentEntity.getBoardEntity().getMemberID());
        return commentDTO;
    }

    public static CommentDTO toCommentDTOWithID(CommentEntity commentEntity, Long userId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentContents(commentEntity.getCommentCentents());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setBoardTitle(commentEntity.getBoardEntity().getBoardTitle());
        commentDTO.setMemberId(userId);
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        return commentDTO;
    }
}
