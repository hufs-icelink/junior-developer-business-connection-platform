<<<<<<< HEAD
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
=======
package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.repository.CareerAPIRepository;
import com.example.demo.service.CertificateAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class CertificateAPIController {
    @Autowired
    private CertificateAPIService certificateAPIService;

    @Autowired
    private CareerAPIRepository careerAPIRepository;

    @PostMapping("/user/certificate-add") // userId 변수로 유저의 아이디 값을 꼭 받아야함
    public void certificateSave(@RequestBody Certificate certificate){
        certificateAPIService.write(certificate);
    }

    //유저의 자격증에 대한 Get API는 userAPIController 에서 사용



    //자격증의 cerName(이름)울 변경하기위한 put
    @PutMapping("/user/certificate/re-write/{id}") //url로 cereer의 id를 전달
    Certificate reWriteCert(@PathVariable Integer id,@RequestBody Certificate newCertificate){
        return certificateAPIService.reWrite(newCertificate, id);
    }

}
>>>>>>> 7a4a0c2f8a309569f353b3d9c21ba0cde5f79e9f
