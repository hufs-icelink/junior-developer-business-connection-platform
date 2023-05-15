package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // --> html파일에서 요청이 발생하면 가장먼저 받아주는 부분
public class HomeController {
    // 기본페이지 요청 메서드
    @GetMapping("/")
    public String index() {  //기본주서 요청이 들어오면 리 메소드가 호출이 된다
        return "index";      //즉, 요청이 왔을 대 "index"라는 html파일 브라우저에 띄움
        //=> templates 폴더의 index.html을 찾아감

    }
}
