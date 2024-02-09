package com.gdy.board_project.Service;


import com.gdy.board_project.Dto.BoardCntDTO;
import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Entity.BoardEntity;
import com.gdy.board_project.Entity.MemberEntity;
import com.gdy.board_project.Enum.BoardCategory;
import com.gdy.board_project.Repository.BoardRepository;
import com.gdy.board_project.Repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
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
            boardDTOList.add(BoardDTO.toBoardDTOList(boardEntity, boardEntity.getId(),category, boardEntity.getMemberName()));
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
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity,category,boardEntity.getMemberName());
            return boardDTO;
        }
        else{
            return null;
        }
    }


    public BoardDTO updateForm(BoardCategory boardCategory, Long id, HttpSession session) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        Long myId = ((MemberDTO) session.getAttribute("member")).getId(); //해당 세션 id
        String category = boardCategory.name();
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            if(myId.equals(boardEntity.getMemberID())){ //현재 세션과 현재 로그인id값이 같으면
                BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity,category,boardEntity.getMemberName());
                return boardDTO;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }


    public BoardDTO update(BoardCategory boardCategory, Long boardid,BoardDTO boardDTO,Long memberId) {
        //boardRepositiory.save 가 insert인지, update인지 판단 방법 : id가 있냐 없냐
        //id가 없으면 insert, id가 있으면 update
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);
        String category = boardCategory.name();

        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardCategory ,boardDTO, memberEntity);
            boardRepository.save(boardEntity);
            return findByCategoryandId(boardCategory,boardid);
        }else{
            return null;
        }

    }

    public boolean delete(Long id, HttpSession session) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        Long myId = ((MemberDTO) session.getAttribute("member")).getId(); //현재 로그인한 id
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            if(myId.equals(boardEntity.getMemberID())){ //현재 세션과 현재 로그인id값이 같으면
                boardRepository.deleteById(id);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }

    public List<BoardDTO> findAll(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).get();
        List<BoardEntity> boardEntityList = boardRepository.findAllByMemberEntityOrderByIdDesc(memberEntity);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            BoardDTO boardDTO = BoardDTO.toBoardDTOWithID(boardEntity,id);
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }
}
