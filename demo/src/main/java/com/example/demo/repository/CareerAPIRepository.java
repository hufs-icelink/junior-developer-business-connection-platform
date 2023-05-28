package com.example.demo.repository;

import com.example.demo.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerAPIRepository extends JpaRepository<Career, Integer> {
}
