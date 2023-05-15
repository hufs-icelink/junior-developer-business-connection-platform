package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/board/boardWrite")
    public String saveFrom(){
        return "boardWrite";
    }

    @PostMapping("/board/boardWrite")
    public String boardSave(Board board) {
        boardService.write(board);

        return "index";
    }


}
