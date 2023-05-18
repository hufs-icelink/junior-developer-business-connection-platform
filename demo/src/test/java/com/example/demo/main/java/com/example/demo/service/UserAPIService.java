package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAPIService {
    @Autowired
    private UserAPIRepository userAPIRepository;

    public String write(User user){userAPIRepository.save(user);
        return null;
    }






}