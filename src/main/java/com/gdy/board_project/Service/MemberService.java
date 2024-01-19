package com.gdy.board_project.Service;

import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Entity.MemberEntity;
import com.gdy.board_project.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void join(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
       // 1. 회원이 입력한 이메일로 db에서 조회
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        //2. db에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단.
        if(byMemberEmail.isPresent()){ //조회 결과가 있다.
            MemberEntity memberEntity = byMemberEmail.get(); //엔티티 객체 가져옴
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){ //비밀번호 일치
                // entity ->dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else{ //비밀번호 불일치
                return null;
            }
        }
        else{ //조회 결과가 없다.
            return null;
        }
    }

}
