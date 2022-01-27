package com.spring.mvc.controller;

import com.spring.mvc.domain.LoginMember;
import com.spring.mvc.domain.Member;
import com.spring.mvc.repository.MemberRepository;
import lombok.AllArgsConstructor;
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
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/main")
    public String home() {
        return "home";
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
