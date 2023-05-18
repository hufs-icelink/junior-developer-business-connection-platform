package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class UserController {
    @Autowired
    private UserService userService; //생성자 주입: controller가 service의 자원을 사용가능해짐
    //회원가입 페이지 출력 요청


    @GetMapping("/user/save") //링크를 클락해서 요청하는것은 모두다 get메소드
    public String saveForm() { //화면을 리턴하는 방식으로는 string메소드 사용
        return "save";
    }


    @PostMapping("/user/save") //"/member/save"경로로 post방식으로 보내진 데이타를 받는 controller메소드
    public String userSave(User user) { //파라미터 하나로 회원정보 모든 데이터를 받을수 있다
        userService.write(user);

        return "index";
    }

}
