package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private final MemberService memberService;
    public ApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/getList")
    public List<User> getMemberList() {
        return memberService.getMemberListLimit10();
    }
}
