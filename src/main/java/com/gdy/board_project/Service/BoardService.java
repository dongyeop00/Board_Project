package com.gdy.board_project.Service;


import com.gdy.board_project.Dto.BoardCntDTO;
import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.MemberEntity;
import com.gdy.board_project.Enum.BoardCategory;
import com.gdy.board_project.Repository.BoardRepository;
import com.gdy.board_project.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void save(BoardCategory boardCategory, BoardDTO boardDTO, Long myId) {
        //부모엔티티 조회하기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(myId);

        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardCategory ,boardDTO, memberEntity);
            boardRepository.save(boardEntity);
        }
    }


    public BoardCntDTO getBoardCnt() {
        return BoardCntDTO.builder().totalBoardCnt(boardRepository.count()).build();
    }

    public List<BoardDTO> findboard(BoardCategory boardCategory) {
        List<BoardEntity> boardEntityList = boardRepository.findByCategory(boardCategory);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        String category = boardCategory.name();
        for(BoardEntity boardEntity : boardEntityList){
            System.out.println(boardDTOList);
            boardDTOList.add(BoardDTO.toBoardDTOList(boardEntity, boardEntity.getId(),category));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }


    public BoardDTO findByCategoryandId(BoardCategory boardCategory, Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        String category = boardCategory.name();

        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity,category);
            return boardDTO;
        }
        else{
            return null;
        }


        /*
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
         */
    }
}
