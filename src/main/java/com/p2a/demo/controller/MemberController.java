package com.p2a.demo.controller;

import com.p2a.demo.model.Member;
import com.p2a.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member(new AtomicReference<String>("Han"));
        member.setName(new AtomicReference<String>(form.getName()));

        System.out.println(member.getName());

        memberService.join(new AtomicReference<Member>(member));
        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model){
        List<AtomicReference<Member>> members = memberService.findMembers();
        model.addAttribute("members", members.stream()
                .map(v->v.get())
                .sorted((v1, v2) -> v2.getId().intValue()-v1.getId().intValue())
                .collect(Collectors.toList()));
        /*members.stream().map(v->v.get()).collect(Collectors.toList()).forEach(v -> {
            System.out.println(v.getId());
            System.out.println(v.getName());
        });*/
        return "members/memberList";
    }
}
