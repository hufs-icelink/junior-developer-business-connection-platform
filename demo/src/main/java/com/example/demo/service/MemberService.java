package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.RoleType;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService{

    @Autowired
    private MemberRepository memberRepository;


    public void write(User user, MultipartFile file) throws Exception
    {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        //저장할 경로 지정

        UUID uuid = UUID.randomUUID();
        //고유한 이름을 갖기위해서 파일 이름에 붙일 랜덤 number 생성

        String fileName = uuid + "_" + file.getOriginalFilename();
        //저장될 파일 이름: random number + 원래 파일 이름

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile); //MultipartFile의 file데이터를 seveFile 객체에 복사.

        user.setPictureName(fileName);
        user.setPicturePath("/files/" + fileName);
        user.setRole(RoleType.valueOf("Member"));

        memberRepository.save(user);

    }

    @Transactional
    public User login(User member) {

        User m1 = memberRepository.findByIdAndUserPassword(member.getId(), member.getUserPassword()).orElseGet(()-> {
            return null;
        });

        System.out.println("로그인 완료");

        return m1;
    }

    @Transactional
    public List<User> getMemberListLimit10 () {
        List<User> memberList = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
        memberList = memberList.subList(0, (memberList.size() >= 10) ? 10 : memberList.size());

        return memberList;
    }
}
