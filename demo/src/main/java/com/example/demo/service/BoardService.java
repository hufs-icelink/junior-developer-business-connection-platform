package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService{
    @Autowired
    private BoardRepository boardRepository;



    public void write(@NotNull Board board) {
        board.setDate(LocalDateTime.now());
        boardRepository.save(board);
    }


    public List<Board> findAll() {
        List<Board> boardList = boardRepository.findAll();


        return boardList;
    }

}