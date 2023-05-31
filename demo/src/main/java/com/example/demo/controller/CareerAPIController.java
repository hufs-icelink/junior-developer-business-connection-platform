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
