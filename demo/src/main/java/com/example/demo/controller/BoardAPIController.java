package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardAPIRepositoty;
import com.example.demo.service.BoardAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardAPIController {
    @Autowired
    private BoardAPIRepositoty boardAPIRepositoty;

    @Autowired
    private BoardAPIService boardAPIService;

    @PostMapping("/board/boardWrite")
    Board boardSave(@RequestBody Board board){
        return boardAPIService.write(board);
    }

    @GetMapping("/boards")
    List<Board> allBoards(){
        return boardAPIRepositoty.findAll();
    }









}