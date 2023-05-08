package com.example.demo.controller;

import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final MemberService memberService;



    public ApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/login")
    public void login(@RequestBody MemberEntity member, HttpServletRequest request) {

        System.out.println(member.getId());
        System.out.println(member.getUserPassword());

        MemberEntity m1 = memberService.login(member);

        if(m1 == null) {
            System.out.println("존재하지 않는 사용자입니다.");
            return;
        }


        HttpSession session = request.getSession();
        session.setAttribute("id", m1.getId());

        session.setMaxInactiveInterval(30*60);
    }
}
