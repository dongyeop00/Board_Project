package com.gdy.board_project.Controller;

import com.gdy.board_project.Dto.MemberDTO;
import com.gdy.board_project.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

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
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        MemberDTO loginResult = memberService.login(memberDTO);
        System.out.println("Controller dto : " + loginResult);
        if(loginResult != null){ //login 성공
            session.setAttribute("member",loginResult);
            System.out.println("if : " + loginResult);
            return "redirect:/"; //redirect하면 모든 model값들이 사라져 session에 저장한다. 저장한 값은 따로 다시 사용 가능
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
        System.out.println(memberDTO);
        model.addAttribute("member",memberDTO);
        return "detail"; //값들을 detail로 가져간다
    }

    @GetMapping("/update")
    public String update(HttpSession session, Model model){
        //내 정보는 session에 담겨져 있다.
        //session에 email값을 가져오고, email 값으로 db 전체 정보 가져오기

        String myEmail = ((MemberDTO) session.getAttribute("member")).getMemberEmail();
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        //MemberDTO memberDTO = memberService.updateForm(sessionDTO);
        model.addAttribute("updateMember",memberDTO);
        return "update";
    }

    @PostMapping("/update")
    public String updateForm(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/detail/"+memberDTO.getId();
    }
}
