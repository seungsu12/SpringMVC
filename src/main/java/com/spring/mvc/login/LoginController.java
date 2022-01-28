package com.spring.mvc.login;


import com.spring.mvc.member.MemberService;
import com.spring.mvc.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("member") LoginMember Loginmember) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "login";
        }

        Member member = memberService.login(loginMember.getLoginId(), loginMember.getPassword());
        if (member == null) {
            bindingResult.reject("globalError", "아이디 또는 패스워드가 정확하지 않습니다");
            return "main";
        }
        // 쿠키에 시간설정을 하지않으면, 세션 쿠키이다.
        Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(idCookie);
        log.info("login user = {}", member);

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response,"memberId");
        return "redirect:/main";
    }

    public void expireCookie(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
