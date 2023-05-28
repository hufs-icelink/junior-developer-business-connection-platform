package com.example.demo.service;

import com.example.demo.entity.Career;
import com.example.demo.entity.User;
import com.example.demo.repository.CareerAPIRepository;
import com.example.demo.repository.UserAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerAPIService {
    @Autowired
    private CareerAPIRepository careerAPIRepository;

    @Autowired
    private UserAPIRepository userAPIRepository;

    public void write(Career career) {
        User user = userAPIRepository.findByName(career.getUserName()).orElseGet(()-> {
            return null;
        });

        if(user == null) {
            System.out.println("에러");
        }
        else{
            career.setUser(user);
        }

        careerAPIRepository.save(career);
    }
}
