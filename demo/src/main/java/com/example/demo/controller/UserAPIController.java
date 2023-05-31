package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.BoardAPIRepositoty;
import com.example.demo.repository.UserAPIRepository;
import com.example.demo.service.UserAPIService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserAPIController {

    @Autowired
    private UserAPIRepository userAPIRepository;
    @Autowired
    private UserAPIService userAPIService; //생성자 주입: controller가 service의 자원을 사용가능해짐

    @Autowired
    private BoardAPIRepositoty boardAPIRepositoty;

    @PostMapping("/user/user-register") //오직 회원가입을 위한 post api
    User userSave(@RequestBody User user) {
        return userAPIService.write(user);
    }

    @GetMapping("/users")
    List<User> allUsers(){
        return userAPIRepository.findAll();
    }


    @PostMapping("/user/login")
    public void userLogin(@RequestBody User user, HttpServletRequest request) {

        User user1 = userAPIService.login(user);

        if(user1 != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", user1.getId());
            session.setAttribute("name", user1.getName());

            session.setMaxInactiveInterval(30 * 60 * 60);
        }

    }


    // password, name, mail, age, gitId, blogId, address, pay,
    // skill, univerGrade, jod, shortRes, picture를 위한 PUT API
    @PutMapping("/user/information-change") //유저의 ID를 JSON으로 받음
    User replaceUser(@RequestBody User newUser, MultipartFile file) throws Exception {
        //file -> 이미지를 위한 변수
        //이미지를 저장하기 위해 resources에서 static이라는 디렉터리만들고,static디렉터리안에 또 files이라는 디렉터리 만들어야함.
        String id;
        id = newUser.getId();
        return userAPIService.reWrite(newUser, id, file);
    }


    @GetMapping("/user") //한 유저에 대한 GET API (Json 형태로 유저의 아이디를 id 변수로 전달해야함)
    Optional<User> oneUser(@RequestBody User user){ //해당 유저가 작성한 게시글,경력,자격증 데이터 받을 수 있다
        String id = user.getId();
        return userAPIRepository.findById(id);
    }



}
