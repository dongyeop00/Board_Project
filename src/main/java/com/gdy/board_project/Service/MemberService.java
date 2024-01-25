package com.gdy.board_project.Service;

import com.gdy.board_project.Dto.MemberCntDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Entity.MemberEntity;
import com.gdy.board_project.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
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
                System.out.println(dto);
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

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        //updateMemberEntity를 안만들어주면 insert가 되어버린다.
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }
        else{
            return null;
        }
    }

    public MemberCntDTO getMemberCnt() {
        return MemberCntDTO.builder().totalUserCnt(memberRepository.count()).build();
    }

    public void deleteByid(Long id) {
        memberRepository.deleteById(id);
    }
}
