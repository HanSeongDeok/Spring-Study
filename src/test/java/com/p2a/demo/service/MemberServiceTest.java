package com.p2a.demo.service;

import com.p2a.demo.model.Member;
import com.p2a.demo.repository.MemberRepository;
import com.p2a.demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    AtomicReference<String> name = new AtomicReference<>("Spring");

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void join() {
        // given
        AtomicReference<Member> member = new AtomicReference<>();
        member.set(new Member(name));

        // when
        AtomicLong saveId = memberService.join(member);

        // then
        AtomicReference<Member> findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.get().getName()).isEqualTo(findMember.get().getName());
    }

    @Test
    public void duplicatedMemberException(){
        //given
        AtomicReference<Member> member1 = new AtomicReference<>();
        member1.set(new Member(name = new AtomicReference<>("Spring")));

        AtomicReference<Member> member2 = new AtomicReference<>();
        member2.set(new Member(name = new AtomicReference<>("Spring")));
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

      /*  try {
            memberService.join(member2);
            //fail();
        }catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}