package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.User;
import com.example.demo.repository.CertificateAPIRepository;
import com.example.demo.repository.UserAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CertificateAPIService {

    @Autowired
    private CertificateAPIRepository certificateAPIRepository;

    @Autowired
    private UserAPIRepository userAPIRepository;

    public void write(Certificate certificate) {
        User user = userAPIRepository.findByName(certificate.getUserName()).orElseGet(()-> {
            return null;
        });

        if(user == null) {
            System.out.println("에러");
        }
        else{
            certificate.setUser(user);
        }

        certificateAPIRepository.save(certificate);
    }
}
