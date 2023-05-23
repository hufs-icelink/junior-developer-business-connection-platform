package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserAPIRepository;
import com.example.demo.service.UserAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserAPIController {

    @Autowired
    private UserAPIRepository userAPIRepository;
    @Autowired
    private UserAPIService userAPIService; //생성자 주입: controller가 service의 자원을 사용가능해짐

    @PostMapping("/users")
    User userSave(@RequestBody User user, MultipartFile file) throws Exception {

        return userAPIService.write(user, file); //file -> 이미지를 위한 변수
        //이미지를 저장하기 위해 resources에서 static이라는 디렉터리만들고,static디렉터리안에 또 files이라는 디렉터리 만들어야함.
    }
}
