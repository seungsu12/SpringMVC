package com.spring.mvc.controller;


import com.spring.mvc.Service.MemberService;
import com.spring.mvc.domain.LoginMember;
import com.spring.mvc.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String login(@Validated @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors ={}",bindingResult);
            return "login";
        }

         Member member = memberService.login(loginMember.getLoginId(),loginMember.getPassword());
        if (member == null) {
            bindingResult.reject("globalError","아이디 또는 패스워드가 정확하지 않습니다");
            return "login";
        }



        return "redirect:/home";
    }
}
