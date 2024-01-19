package com.gdy.board_project.Controller;

import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO memberDTO){
        memberService.join(memberDTO);
        return "index";
    }

    @PostMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        MemberDTO loginResult = memberService.login(memberDTO);
        System.out.println("loginResult :" + loginResult);
        if(loginResult != null){ //login 성공
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            model.addAttribute("member",loginResult);
            System.out.println("그냥 : " + memberDTO);
            return "redirect:/";
        }
        else{ //login 실패
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "detail"; //값들을 detail로 가져간다
    }
}
