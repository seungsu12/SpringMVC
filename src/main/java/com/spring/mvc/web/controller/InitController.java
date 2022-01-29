package com.spring.mvc.web.controller;

import com.spring.mvc.web.member.Member;
import com.spring.mvc.web.member.MemberRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitController {


    private final MemberRepository memberRepository;

    public InitController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostConstruct
    public void Init() {
        Member member1 =new Member("dog","dog1","옆집개");
        Member member2 =new Member("cat","cat1","옆집고양이");

        memberRepository.saveMember(member1);
        memberRepository.saveMember(member2);

    }
}
