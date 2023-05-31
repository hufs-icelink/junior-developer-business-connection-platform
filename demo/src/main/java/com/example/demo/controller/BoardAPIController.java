package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepositoty;
import com.example.demo.service.BoardAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BoardAPIController {
    @Autowired
    private BoardRepositoty boardRepositoty;

    @Autowired
    private BoardAPIService boardAPIService;

    @PostMapping("/board/boardWrite") //userName(유저의 이름) 값을 꼭 받아야함
    public void boardSave(@RequestBody Board board){
        boardAPIService.write(board);
    }

    @GetMapping("/boards")
    List<Board> allBoards(){
        return boardRepositoty.findAll();
    }



    //title, content, link, note를 위한 PUT API
    @PutMapping("/board/{id}") //게시글의 id를 URL로 받음 ex) /api/board/12
    Board replaceUser(@PathVariable Integer id, @RequestBody Board newBoard) {
        return boardAPIService.reWrite(newBoard,id);
    }









}