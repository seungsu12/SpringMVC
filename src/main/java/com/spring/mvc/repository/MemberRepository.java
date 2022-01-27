package com.spring.mvc.repository;

import com.spring.mvc.domain.LoginMember;
import com.spring.mvc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class MemberRepository {

    private static HashMap<Long, Member> store = new HashMap<Long,Member>();
    private static Long sequence = 0L;

    public Member saveMember(Member member) {
        member.setId(++sequence);
        log.info("save Member = {}",member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return  findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
