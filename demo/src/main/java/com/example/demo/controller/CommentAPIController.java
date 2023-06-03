package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.repository.BoardAPIRepositoty;
import com.example.demo.repository.CommentAPIRepository;
import com.example.demo.service.CommentAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentAPIController {

    @Autowired
    private CommentAPIService commentAPIService;

    @Autowired
    private CommentAPIRepository commentAPIRepository;

    @Autowired
    private BoardAPIRepositoty boardAPIRepositoty;

    @PostMapping("/board/{id}") //댓글이 달리는 게시글 id는 url로 주고, 작성자 id는 userId 변수로 JSON 형태로 전달해야함
    public void commentSave(@PathVariable Integer id, @RequestBody Comment comment){
        commentAPIService.write(comment, id);

    }

    @PutMapping("/board/{id}")
    Comment reWrite(@PathVariable Integer id,@RequestBody Comment newComment){
        return commentAPIService.reWrite(newComment, id);
    }

    //comment에 대한 Get API는 boardAPIController 에서 사용

}
