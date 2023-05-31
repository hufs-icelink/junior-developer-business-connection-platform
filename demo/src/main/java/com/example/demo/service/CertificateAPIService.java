package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.User;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CertificateAPIService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private UserRepository userRepository;

    public void write(Certificate certificate) {
        User user = userRepository.findByName(certificate.getUserName()).orElseGet(()-> {
            return null;
        });

        if(user == null) {
            System.out.println("에러");
        }
        else{
            certificate.setUser(user);
        }

        certificateRepository.save(certificate);
    }
}
