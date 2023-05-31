package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.repository.CareerRepository;
import com.example.demo.service.CertificateAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class CertificateAPIController {
    @Autowired
    private CertificateAPIService certificateAPIService;

    @Autowired
    private CareerRepository careerRepository;

    @PostMapping("/certificate/add") //userName(유저의 이름) 값을 꼭 받아야함
    public void certificateSave(@RequestBody Certificate certificate){
        certificateAPIService.write(certificate);
    }

}
