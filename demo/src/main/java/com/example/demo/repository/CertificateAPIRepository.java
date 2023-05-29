package com.example.demo.repository;

import com.example.demo.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateAPIRepository extends JpaRepository<Certificate, Integer> {
}
