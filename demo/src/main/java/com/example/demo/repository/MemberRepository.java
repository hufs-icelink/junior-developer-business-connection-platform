package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<User, String> {

    Optional<User> findByIdAndUserPassword(String id, String userPassword);
    Optional<User> findById(String id);

}



