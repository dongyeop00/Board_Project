package com.gdy.board_project.Controller;

import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Enum.BoardCategory;
import com.gdy.board_project.Service.BoardService;
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
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/{category}")
    public String list(@PathVariable String category, Model model){
        BoardCategory boardCategory = BoardCategory.of(category);

        List<BoardDTO> boardList = boardService.findboard(boardCategory);

        model.addAttribute("boardList", boardList);

        return "/board/list";
    }

    @GetMapping("/{category}/write")
    public String writeForm(@PathVariable String category, HttpSession session, Model model){
        BoardCategory boardCategory = BoardCategory.of(category);
        System.out.println(boardCategory);
        String id = session.getId();
        System.out.println(id);
        String myEmail = ((MemberDTO) session.getAttribute("member")).getMemberEmail();
        MemberDTO  memberDTO = memberService.updateForm(myEmail); //사용자 정보 가져옴

        model.addAttribute("user",memberDTO);
        model.addAttribute("category",category);

        return "/board/write";
    }

    @PostMapping("/{category}/write")
    public String write(@PathVariable String category, HttpSession session, @ModelAttribute BoardDTO boardDTO){
        BoardCategory boardCategory = BoardCategory.of(category);
        Long myId = ((MemberDTO) session.getAttribute("member")).getId();
        boardService.save(boardCategory, boardDTO, myId);
        return "redirect:/board/" + category;
    }

    @GetMapping("/{category}/{id}")
    public String detail(@PathVariable String category, @PathVariable Long id, Model model){
        BoardCategory boardCategory = BoardCategory.of(category);
        // 1. 게시글 조회 1 올리기
        boardService.updateHits(id);

        // 2. 게시글 데이터를 가져와서 detail.html에 출력한다.
        BoardDTO boardDTO = boardService.findByCategoryandId(boardCategory,id);
        model.addAttribute("board",boardDTO);
        return "/board/detail";
    }

    @GetMapping("/{category}/update/{id}")
    public String updateForm(@PathVariable String category, @PathVariable Long id, Model model){
        BoardCategory boardCategory = BoardCategory.of(category);
        BoardDTO boardDTO = boardService.findByCategoryandId(boardCategory,id); //id로 값들을 찾아 dto에 저장
        model.addAttribute("boardUpdate", boardDTO); //저장한 값을 model에 담아 전달
        return "/board/update";
    }

    @PostMapping("/{category}/update")
    public String update(@PathVariable String category, Model model){
        return null;
    }

}
