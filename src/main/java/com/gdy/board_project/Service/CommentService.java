package com.gdy.board_project.Service;

import com.gdy.board_project.Dto.CommentCntDTO;
import com.gdy.board_project.Dto.CommentDTO;
import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.CommentEntity;
import com.gdy.board_project.Entity.MemberEntity;
import com.gdy.board_project.Repository.BoardRepository;
import com.gdy.board_project.Repository.CommentRepository;
import com.gdy.board_project.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        // 부모 엔티티 조회
        Optional<BoardEntity> boardEntity = boardRepository.findById(commentDTO.getBoardId());
        Optional<MemberEntity> memberEntity = memberRepository.findById(commentDTO.getMemberId());
        if(boardEntity.isPresent() && memberEntity.isPresent()) {
            BoardEntity board = boardEntity.get();
            MemberEntity member = memberEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, board, member);
            return commentRepository.save(commentEntity).getId();
        }else{
            return null;
        }
    }

    @Transactional
    public List<CommentDTO> findAll(Long boardId, Long memberId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        MemberEntity memberEntity = memberRepository.findById(memberId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityAndMemberEntityOrderByIdDesc(boardEntity, memberEntity);
        // EntityList -> DTOList로 변환
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList){
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardEntity, memberEntity);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    @Transactional
    public List<CommentDTO> findComment(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList){
            CommentDTO commentDTO = CommentDTO.toCommentDTOList(commentEntity, id);
            System.out.println("어디갓노 : " + commentDTO);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    public CommentCntDTO getCommentCnt() {
        return CommentCntDTO.builder().totalCommentCnt(commentRepository.count()).build();
    }

    @Transactional
    public List<CommentDTO> findMyComment(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByMemberEntityOrderByIdDesc(memberEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList){
            CommentDTO commentDTO = CommentDTO.toCommentDTOWithID(commentEntity,id);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
