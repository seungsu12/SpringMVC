package com.spring.mvc.web.member;

import com.spring.mvc.web.SessionConst;
import com.spring.mvc.web.login.LoginMember;
import com.spring.mvc.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor

public class MemberController {

    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;


//    @GetMapping("/main")
//    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
//        if (memberId == null) {
//            return "home";
//        }
//        //로그인
//        Member loginMember = memberRepository.findById(memberId);
//        if (loginMember == null) {
//            return "home";
//        }
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//    }
//    @GetMapping("/main")
//    public String homeLogin_V2(HttpServletRequest request, Model model) {
//
//
//        LoginMember member = (LoginMember) sessionManager.getSession(request);
//        log.info("session = {}",member);
//        if (member == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", member);
//        return "loginHome";
//    }

//    @GetMapping("/")
//    public String homeLogin_V3(HttpServletRequest request, Model model) {
//
//        HttpSession session = request.getSession(false);
//
//        if (session == null) {
//            return "home";
//        }
//        LoginMember member = (LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        if (member == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", member);
//        return "loginHome";
//    }

    @GetMapping("/")
    public String homeLogin_V3Spring(@SessionAttribute(name=SessionConst.LOGIN_MEMBER,required = false)LoginMember member, Model model) {


        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute Member member) {

        return "join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute Member member, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "join";
        }

        memberRepository.saveMember(member);
        return "redirect:/main";
    }


}
