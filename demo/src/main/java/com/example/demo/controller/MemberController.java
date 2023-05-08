package com.example.demo.controller;

import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller //@를 붙이면 스프링이 괸리하는 객체로 등록 -> 객체 주입 방식 3가지 존재
 //생성자 주입을 위한 롬복
public class MemberController {

    //객체 주입 방식 중 하나: 생성자 주입
    @Autowired
    private MemberService memberService; //생성자 주입: controller가 service의 자원을 사용가능해짐
    //회원가입 페이지 출력 요청


    @GetMapping("/member/save") //링크를 클락해서 요청하는것은 모두다 get메소드
    public String saveForm() { //화면을 리턴하는 방식으로는 string메소드 사용
        return "save";
    }


    @PostMapping("/member/save") //"/member/save"경로로 post방식으로 보내진 데이타를 받는 controller메소드
    public String userSave(MemberEntity memberEntity, MultipartFile file) throws Exception { //파라미터 하나로 회원정보 모든 데이터를 받을수 있다
        memberService.write(memberEntity, file);

        return "index";
    }

}
