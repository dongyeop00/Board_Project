package com.gdy.board_project.Service;


import com.gdy.board_project.Dto.BoardCntDTO;
import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.MemberEntity;
import com.gdy.board_project.Enum.BoardCategory;
import com.gdy.board_project.Repository.BoardRepository;
import com.gdy.board_project.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoarderService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /*
    public List<BoardDTO> findboard() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            System.out.println(boardDTOList);
            boardDTOList.add(BoardDTO.toBoardDTOList(boardEntity, boardEntity.getId()));
        }
        return boardDTOList;
    }
*/

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

    public List<BoardEntity> getNotice(BoardCategory boardCategory) {
        return boardRepository.findAllByCategory(boardCategory);
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
}
