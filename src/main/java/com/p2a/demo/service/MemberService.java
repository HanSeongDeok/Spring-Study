package com.p2a.demo.service;

import com.p2a.demo.model.Member;
import com.p2a.demo.repository.MemberRepository;
import com.p2a.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public AtomicLong join(AtomicReference<Member> member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.get().getId();
    }
    private void validateDuplicateMember(AtomicReference<Member> member) {
        memberRepository.findIdByName(member.get().getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<AtomicReference<Member>> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * Id로 이름 조회
     */
    public Optional<AtomicReference<Member>> findOne(AtomicLong memberId){
        return memberRepository.findNameById(memberId);
    }
}
