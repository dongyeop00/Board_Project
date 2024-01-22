package com.gdy.board_project.Controller;

import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Service.BoarderService;
import com.gdy.board_project.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoarderService boarderService;
    private final MemberService memberService;

    @GetMapping("/{category}")
    public String list(@PathVariable String category, Model model){
        List<BoardDTO> boardDTOList = boarderService.findboard();
        System.out.println("boardDTOList = " + boardDTOList);
        model.addAttribute("boardList",boardDTOList);
        return "/board/list";
    }

    @GetMapping("/hello/write")
    public String helloWrite(HttpSession session, Model model){
        String myEmail = ((MemberDTO) session.getAttribute("member")).getMemberEmail();
        MemberDTO memberDTO = memberService.updateForm(myEmail); //사용자 정보 가져옴
        //write memberDTO : MemberDTO(id=1, memberEmail=ed9466@naver.com, memberPassword=qwe123, memberName=구동엽)
        System.out.println("write memberDTO : " + memberDTO);

        model.addAttribute("user",memberDTO);
        return "/board/write";
    }

    @PostMapping("/hello/write")
    public String write(@ModelAttribute BoardDTO boardDTO, HttpSession session){
        Long myId = ((MemberDTO) session.getAttribute("member")).getId();
        System.out.println("bardDTO = " + boardDTO);
        System.out.println("myid = " + myId); //아이디 얻었다.
        boarderService.save(boardDTO,myId);
        return "redirect:/board/hello";
    }


}
