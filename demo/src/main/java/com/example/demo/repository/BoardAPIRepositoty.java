package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface BoardAPIRepositoty extends JpaRepository<Board, Integer> {
    Optional<Board> findByUserName(String userName);


}
