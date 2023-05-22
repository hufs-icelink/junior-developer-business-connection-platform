package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardAPIRepositoty;
import com.example.demo.service.BoardAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardAPIController {
    @Autowired
    private BoardAPIRepositoty boardAPIRepositoty;

    @Autowired
    private BoardAPIService boardAPIService;

    @PostMapping("/board")
    Board boardSave(@RequestBody Board board){
        return boardAPIService.write(board);
    }









}
