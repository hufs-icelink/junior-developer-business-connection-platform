package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardService{

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {

        board.setDate(LocalDateTime.now());

        boardRepository.save(board);
    }


}