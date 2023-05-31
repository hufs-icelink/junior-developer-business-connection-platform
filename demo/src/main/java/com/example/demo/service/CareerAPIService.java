package com.example.demo.service;

import com.example.demo.entity.Career;
import com.example.demo.entity.User;
import com.example.demo.repository.CareerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerAPIService {
    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private UserRepository userRepository;

    public void write(Career career) {
        User user = userRepository.findByName(career.getUserName()).orElseGet(()-> {
            return null;
        });

        if(user == null) {
            System.out.println("에러");
        }
        else{
            career.setUser(user);
        }

        careerRepository.save(career);
    }
}
