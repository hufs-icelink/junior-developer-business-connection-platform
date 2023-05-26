package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardAPIRepositoty;
import com.example.demo.service.BoardAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BoardAPIController {
    @Autowired
    private BoardAPIRepositoty boardAPIRepositoty;

    @Autowired
    private BoardAPIService boardAPIService;

    @PostMapping("/board/boardWrite")
    public void boardSave(@RequestBody Board board){
        boardAPIService.write(board);
    }

    @GetMapping("/boards")
    List<Board> allBoards(){
        return boardAPIRepositoty.findAll();
    }









}