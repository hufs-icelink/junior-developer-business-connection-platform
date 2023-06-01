<<<<<<< HEAD
package com.example.demo.controller;

import com.example.demo.entity.Career;
import com.example.demo.repository.CareerRepository;
import com.example.demo.service.CareerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class CareerAPIController {

    @Autowired
    private CareerAPIService careerAPIService;

    @Autowired
    private CareerRepository careerRepository;

    @PostMapping("/career/add")  // userName(유저의 이름) 값을 꼭 받아야함
    public void careerSave(@RequestBody Career career) {
         careerAPIService.write(career);
    }
}
=======
package com.example.demo.controller;

import com.example.demo.entity.Career;
import com.example.demo.repository.CareerAPIRepository;
import com.example.demo.service.CareerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class CareerAPIController {

    @Autowired
    private CareerAPIService careerAPIService;

    @Autowired
    private CareerAPIRepository careerAPIRepository;

    @PostMapping("/user/career-add")  // userId 변수로 유저의 아이디 값을 꼭 받아야함
    public void careerSave(@RequestBody Career career) {
         careerAPIService.write(career);
    }

    //유저의 경력에 대한 Get API는 userAPIController 에서 사용



    //경력의 content를 변경하기위한 put
    @PutMapping("/user/career/re-write/{id}") //url로 cereer의 id를 전달
    Career reWriteCareer(@PathVariable Integer id,@RequestBody Career newCareer){
        return careerAPIService.reWrite(newCareer, id);

    }


}
>>>>>>> 7a4a0c2f8a309569f353b3d9c21ba0cde5f79e9f
