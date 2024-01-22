package com.gdy.board_project.Service;


import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.MemberEntity;
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

    public List<BoardDTO> findboard() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            System.out.println(boardDTOList);
            boardDTOList.add(BoardDTO.toBoardDTOList(boardEntity, boardEntity.getId()));
        }
        return boardDTOList;
    }


    public void save(BoardDTO boardDTO, Long myId) {
        //부모엔티티 조회하기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(myId);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO, memberEntity);
            boardRepository.save(boardEntity);
        }
    }
}
