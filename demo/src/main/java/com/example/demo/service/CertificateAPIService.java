<<<<<<< HEAD
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
=======
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
        User user = userAPIRepository.findById(certificate.getUserId()).orElseGet(()-> {
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

    public Certificate reWrite(Certificate newCertificate, Integer id) {
        return certificateAPIRepository.findById(id)
                .map(certificate -> {
                    certificate.setCerName(newCertificate.getCerName());
                    return certificateAPIRepository.save(certificate);
                })
                .orElseGet(() -> {
                    newCertificate.setId(id);
                    return certificateAPIRepository.save(newCertificate);
                });
    }
}
>>>>>>> 7a4a0c2f8a309569f353b3d9c21ba0cde5f79e9f
