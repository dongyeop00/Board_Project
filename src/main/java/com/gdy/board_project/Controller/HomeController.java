package com.gdy.board_project.Controller;

import com.gdy.board_project.Service.BoarderService;
import com.gdy.board_project.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoarderService boardService;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("memberCntDTO", memberService.getMemberCnt());
        model.addAttribute("boardCntDTO",boardService.getBoardCnt());
        return "index";
    }
}
