package com.spring.mvc.Service;

import com.spring.mvc.domain.LoginMember;
import com.spring.mvc.domain.Member;
import com.spring.mvc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;



    public Member login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);

    }
}
