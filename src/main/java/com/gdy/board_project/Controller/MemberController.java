package com.gdy.board_project.Controller;

import com.gdy.board_project.Dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/join")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        return null;
    }
}
