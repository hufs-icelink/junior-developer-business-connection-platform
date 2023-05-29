package com.example.demo.controller;


import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/board/")
    public  String findAll(Model model) {
        List<Board> boardList = boardService.findAll(); //boardList에 담겨진 데이터를 리스트 객체로 가져가기 위해 model 시용
        //어떠한 html로 가져갈 데이터 있다면 가장 많이 쓰이는 = model


        model.addAttribute("BoardList", boardList); // boardList -> BoardList
        return "boardlist";
    }




}
