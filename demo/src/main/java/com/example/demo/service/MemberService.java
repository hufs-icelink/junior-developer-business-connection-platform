package com.example.demo.service;

import com.example.demo.entity.MemberEntity;
import com.example.demo.model.RoleType;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class MemberService{

    @Autowired
    private MemberRepository memberRepository;


    public void write(MemberEntity memberEntity, MultipartFile file) throws Exception
    {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        //저장할 경로 지정

        UUID uuid = UUID.randomUUID();
        //고유한 이름을 갖기위해서 파일 이름에 붙일 랜덤 number 생성

        String fileName = uuid + "_" + file.getOriginalFilename();
        //저장될 파일 이름: random number + 원래 파일 이름

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile); //MultipartFile의 file데이터를 seveFile 객체에 복사.

        memberEntity.setPictureName(fileName);
        memberEntity.setPicturePath("/files/" + fileName);
        memberEntity.setRole(RoleType.valueOf("Member"));

        memberRepository.save(memberEntity);

    }

    @Transactional
    public MemberEntity login(MemberEntity member) {

        MemberEntity m1 = memberRepository.findByIdAndUserPassword(member.getId(), member.getUserPassword()).orElseGet(()-> {
            return null;
        });

        System.out.println("로그인 완료");

        return m1;
    }





}
