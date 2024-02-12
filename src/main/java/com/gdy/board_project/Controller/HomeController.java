package com.gdy.board_project.Controller;

import com.gdy.board_project.Service.BoardService;
import com.gdy.board_project.Service.CommentService;
import com.gdy.board_project.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("memberCntDTO", memberService.getMemberCnt());
        model.addAttribute("boardCntDTO",boardService.getBoardCnt());
        model.addAttribute("commentCntDTO",commentService.getCommentCnt());
        return "index";
    }
}
