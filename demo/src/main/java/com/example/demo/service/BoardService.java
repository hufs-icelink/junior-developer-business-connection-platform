package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void write(Board board, HttpServletRequest request) {

        HttpSession session = request.getSession();

        User m1 = memberRepository.findById((String)session.getAttribute("id")).orElseGet(()-> {
            return null;
        });

        board.setUser(m1);
        board.setDate(LocalDateTime.now());

        boardRepository.save(board);
    }




}