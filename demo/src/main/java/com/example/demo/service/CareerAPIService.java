<<<<<<< HEAD
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
=======
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
        User user = userAPIRepository.findById(career.getUserId()).orElseGet(()-> {
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

    public Career reWrite(Career newCareer, Integer id) {
        return careerAPIRepository.findById(id)
                .map(career -> {
                    career.setContent(newCareer.getContent());
                    return careerAPIRepository.save(career);
                })
                .orElseGet(() -> {
                    newCareer.setId(id);
                    return careerAPIRepository.save(newCareer);
                });
    }
}
>>>>>>> 7a4a0c2f8a309569f353b3d9c21ba0cde5f79e9f
