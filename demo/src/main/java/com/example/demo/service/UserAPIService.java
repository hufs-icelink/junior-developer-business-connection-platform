package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserAPIService {
    @Autowired
    private UserAPIRepository userAPIRepository;

    public User write(User user) {
        return userAPIRepository.save(user);

    }
    public User login(User user) {
        return userAPIRepository.findById(user.getId()).orElseGet(()->{
            return null;
        });


    }

    public User reWrite(User newUser, String id, MultipartFile file) throws Exception {
        String Id = newUser.getId();
        String password = newUser.getUserPassword();
        String name = newUser.getName();
        String mail = newUser.getMail();
        Integer age = newUser.getAge();
        String gitId = newUser.getGithubId();
        String firstBlogId = newUser.getFirstBlogId();
        String secondBlogId = newUser.getSecondBlogId();
        String thirdBlogId = newUser.getThirdBlogId();
        String address = newUser.getAddress();
        Integer pay = newUser.getPay();
        String firstSkill = newUser.getFirstSkill();
        String secondSkill = newUser.getSecondSkill();
        String thirdSkill = newUser.getThirdSkill();
        Integer univerGrade = newUser.getUniverGrade();
        String job = newUser.getJob();
        String shortRes = newUser.getShortRes();
        if (file == null){
            return userAPIRepository.findById(id)
                    .map(user -> {
                        if (Id != null){
                            user.setId(newUser.getId());
                        }
                        if (password != null){
                            user.setUserPassword(newUser.getUserPassword());
                        }
                        if (name != null){
                            user.setName(newUser.getName());
                        }
                        if (mail != null){
                            user.setMail(newUser.getMail());
                        }
                        if (age != null){
                            user.setAge(newUser.getAge());
                        }
                        if (gitId != null){
                            user.setGithubId(newUser.getGithubId());
                        }
                        if (firstBlogId != null){
                            user.setFirstBlogId(newUser.getFirstBlogId());
                        }
                        if (secondBlogId != null){
                            user.setSecondBlogId(newUser.getSecondBlogId());
                        }
                        if (thirdBlogId != null){
                            user.setThirdBlogId(newUser.getThirdBlogId());
                        }
                        if (address != null){
                            user.setAddress(newUser.getAddress());
                        }
                        if (pay != null){
                            user.setPay(newUser.getPay());
                        }
                        if (firstSkill != null){
                            user.setFirstSkill(newUser.getFirstSkill());
                        }
                        if (secondSkill != null){
                            user.setSecondSkill(newUser.getSecondSkill());
                        }
                        if (thirdSkill != null){
                            user.setThirdSkill(newUser.getThirdSkill());
                        }
                        if (univerGrade != null){
                            user.setUniverGrade(newUser.getUniverGrade());
                        }
                        if (job != null){
                            user.setJob(newUser.getJob());
                        }
                        if (shortRes != null){
                            user.setShortRes(newUser.getShortRes());
                        }
                        return userAPIRepository.save(user);
                    })
                    .orElseGet(() -> {
                        newUser.setId(id);
                        return userAPIRepository.save(newUser);
                    });
        }else {
            return userAPIRepository.findById(id)
                    .map(user -> {
                        if (Id != null){
                            user.setId(newUser.getId());
                        }
                        if (password != null){
                            user.setUserPassword(newUser.getUserPassword());
                        }
                        if (name != null){
                            user.setName(newUser.getName());
                        }
                        if (mail != null){
                            user.setMail(newUser.getMail());
                        }
                        if (age != null){
                            user.setAge(newUser.getAge());
                        }
                        if (gitId != null){
                            user.setGithubId(newUser.getGithubId());
                        }
                        if (firstBlogId != null){
                            user.setFirstBlogId(newUser.getFirstBlogId());
                        }
                        if (secondBlogId != null){
                            user.setSecondBlogId(newUser.getSecondBlogId());
                        }
                        if (thirdBlogId != null){
                            user.setThirdBlogId(newUser.getThirdBlogId());
                        }
                        if (address != null){
                            user.setAddress(newUser.getAddress());
                        }
                        if (pay != null){
                            user.setPay(newUser.getPay());
                        }
                        if (firstSkill != null){
                            user.setFirstSkill(newUser.getFirstSkill());
                        }
                        if (secondSkill != null){
                            user.setSecondSkill(newUser.getSecondSkill());
                        }
                        if (thirdSkill != null){
                            user.setThirdSkill(newUser.getThirdSkill());
                        }
                        if (univerGrade != null){
                            user.setUniverGrade(newUser.getUniverGrade());
                        }
                        if (job != null){
                            user.setJob(newUser.getJob());
                        }
                        if (shortRes != null){
                            user.setShortRes(newUser.getShortRes());
                        }
                        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
                        //저장할 경로 지정

                        UUID uuid = UUID.randomUUID();
                        //고유한 이름을 갖기위해서 파일 이름에 붙일 랜덤 number 생성

                        String fileName = uuid + "_" + file.getOriginalFilename();
                        //저장될 파일 이름: random number + 원래 파일 이름

                        File saveFile = new File(projectPath, fileName);
                        try {
                            file.transferTo(saveFile); //MultipartFile의 file데이터를 seveFile 객체에 복사.
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        user.setPictureName(fileName);
                        user.setPicturePath("/files/" + fileName);
                        return userAPIRepository.save(user);
                    })
                    .orElseGet(() -> {
                        newUser.setId(id);
                        return userAPIRepository.save(newUser);
                    });
        }

    }
}







