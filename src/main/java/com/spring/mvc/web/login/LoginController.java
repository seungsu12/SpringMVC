package com.spring.mvc.web.login;


import com.spring.mvc.web.SessionConst;
import com.spring.mvc.web.member.MemberService;
import com.spring.mvc.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final SessionManager sessionManager;
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("member") LoginMember Loginmember) {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@Validated @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult, HttpServletResponse response) {
//
//        if (bindingResult.hasErrors()) {
//            log.info("errors ={}", bindingResult);
//            return "login";
//        }
//
//        Member member = memberService.login(loginMember.getLoginId(), loginMember.getPassword());
//        if (member == null) {
//            bindingResult.reject("globalError", "아이디 또는 패스워드가 정확하지 않습니다");
//            return "main";
//        }
//        // 쿠키에 시간설정을 하지않으면, 세션 쿠키이다.
//        Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
//        response.addCookie(idCookie);
//        log.info("login user = {}", member);
//
//        return "redirect:/home";
//    }


//    @PostMapping("/login")
//    public String login_V2(@Validated @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult, HttpServletResponse response,HttpServletRequest request) {
//
//        if (bindingResult.hasErrors()) {
//            log.info("errors ={}", bindingResult);
//            return "login";
//        }
//
//        sessionManager.createSession(loginMember,response);
//
//        return "redirect:/main";
//    }

//    @PostMapping("/login")
//    public String login_V3(@Validated @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult, HttpServletResponse response,HttpServletRequest request) {
//
//        if (bindingResult.hasErrors()) {
//            log.info("errors ={}", bindingResult);
//            return "login";
//        }
//        //세션이 있으면 세션 밥ㄴ화, 없으면 신규 세션을 생성
//        HttpSession session = request.getSession();
//        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
//        return "redirect:/";
//    }
//    @PostMapping("/login")
//    public String login_V3(@Validated @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult, HttpServletResponse response,HttpServletRequest request) {
//
//        if (bindingResult.hasErrors()) {
//            log.info("errors ={}", bindingResult);
//            return "login";
//        }
//        //세션이 있으면 세션 밥ㄴ화, 없으면 신규 세션을 생성
//        HttpSession session = request.getSession();
//        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
//
//        return "redirect:/";
//    }
    @PostMapping("/login")
    public String login_V4(@Validated @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult
                            , HttpServletResponse response,HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "login";
        }
        //세션이 있으면 세션 밥ㄴ화, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        String url = request.getParameter("url");
        log.info("redirectURL = {}",url);
        return "redirect:"+url;
    }


//    @PostMapping("/logout")
//    public String logout(HttpServletResponse response) {
//        expireCookie(response,"memberId");
//        return "redirect:/main";
//    }

//    @PostMapping("/logout")
//    public String logout_V2(HttpServletRequest request) {
//        sessionManager.expireSession(request);
//        return "redirect:/main";
//    }

    @PostMapping("/logout")
    public String logout_V3(HttpServletRequest request) {
        HttpSession session =request.getSession(false);
        if (session != null) {
            session.invalidate();;
        }
        return "redirect:/";
    }

    public void expireCookie(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
