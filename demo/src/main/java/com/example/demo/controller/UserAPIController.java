package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserAPIService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAPIController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAPIService userAPIService; //생성자 주입: controller가 service의 자원을 사용가능해짐

    @Autowired
    private BlogService blogService;

    @PostMapping("/user/userRegister") //오직 회원가입을 위한 post api
    User userSave(@RequestBody User user) {
        return userAPIService.write(user);
    }

    @GetMapping("/users")
    List<User> allUsers(){
        return userRepository.findAll();
    }


    @PostMapping("/user/login")
    public void userLogin( User user, HttpServletRequest request) {

        User user1 = userAPIService.login(user);

        if(user1 != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", user1.getId());
            session.setAttribute("name", user1.getName());
            session.setAttribute("blogId", user1.getFirstBlogId());

            session.setMaxInactiveInterval(30 * 60 * 60);
        }

    }




    // id, password, name, mail, age, gitId, blogId, address, pay,
    // skill, univerGrade, jod, shortRes, picture를 위한 PUT API
    @PutMapping("/user") //유저의 ID를 JSON으로 받음
    User replaceUser(@RequestBody User newUser, MultipartFile file) throws Exception {
        //file -> 이미지를 위한 변수
        //이미지를 저장하기 위해 resources에서 static이라는 디렉터리만들고,static디렉터리안에 또 files이라는 디렉터리 만들어야함.
        String id;
        id = newUser.getId();
        return userAPIService.reWrite(newUser, id, file);
    }

    @GetMapping("/user/blog/crawling")
    void blogCrawling(HttpServletRequest request) throws IOException {
        blogService.saveSummarize(request);
    }


}
