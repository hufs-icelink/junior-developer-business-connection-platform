package com.example.demo.repository;

import com.example.demo.entity.Github;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubRepository extends JpaRepository<Github, Integer> {
}
