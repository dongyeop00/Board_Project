package com.gdy.board_project.Controller;

import com.gdy.board_project.Dto.BoardDTO;
import com.gdy.board_project.Dto.CommentDTO;
import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Enum.BoardCategory;
import com.gdy.board_project.Service.BoardService;
import com.gdy.board_project.Service.CommentService;
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
    private final CommentService commentService;

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
        Long myId = ((MemberDTO) session.getAttribute("member")).getId();
        MemberDTO  memberDTO = memberService.findById(myId); //사용자 정보 가져옴

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
    public String detail(@PathVariable String category, @PathVariable Long id, Model model, HttpSession session){
        BoardCategory boardCategory = BoardCategory.of(category);
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        // 1. 게시글 조회 1 올리기
        boardService.updateHits(id);

        // 2. 게시글 데이터를 가져와서 detail.html에 출력한다.
        BoardDTO boardDTO = boardService.findByCategoryandId(boardCategory,id);

        //댓글 목록 가져오기
        List<CommentDTO> commentDTOList = commentService.findComment(id);  //member 값들이 null이 되는걸 해결해야댐
        model.addAttribute("commentList",commentDTOList);

        model.addAttribute("member",memberDTO);
        model.addAttribute("board",boardDTO);
        return "/board/detail";
    }

    @GetMapping("/{category}/update/{id}")
    public String updateForm(@PathVariable String category, @PathVariable Long id, Model model,HttpSession session){
        BoardCategory boardCategory = BoardCategory.of(category); //카테고리 값 받기

        BoardDTO boardDTO = boardService.updateForm(boardCategory,id,session); //카테고리와 id로 값들을 찾아 dto에 저장
        if(boardDTO == null){
            return null;
        }
        model.addAttribute("boardUpdate", boardDTO); //저장한 값을 model에 담아 전달

        return "/board/update";
    }

    @PostMapping("/{category}/update/{id}")
    public String update(@PathVariable String category,@PathVariable Long id, Model model, @ModelAttribute BoardDTO boardDTO,HttpSession session){
        BoardCategory boardCategory = BoardCategory.of(category);
        Long myId = ((MemberDTO) session.getAttribute("member")).getId();

        BoardDTO board = boardService.update(boardCategory,id,boardDTO,myId);
        model.addAttribute("board",board);
        return "/board/detail";
    }

    @GetMapping("/{category}/delete/{id}")
    public String delete(@PathVariable String category, @PathVariable Long id,HttpSession session){
        if(boardService.delete(id,session) !=  false){
            return "redirect:/board/" + category;
        }else{
            return null;
        }
    }

}
