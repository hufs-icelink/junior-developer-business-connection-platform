package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardAPIRepositoty;
import com.example.demo.repository.CommentAPIRepository;
import com.example.demo.repository.UserAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class CommentAPIService {

    @Autowired
    private BoardAPIRepositoty boardAPIRepositoty;

    @Autowired
    private CommentAPIRepository commentAPIRepository;

    @Autowired
    private UserAPIRepository userAPIRepository;

    public void write(Comment comment, Integer id) {
        comment.setDate(Timestamp.valueOf(LocalDateTime.now()));
        User user = userAPIRepository.findById(comment.getUserId()).orElseGet(()-> {
            return null;
        });
        Board board = boardAPIRepositoty.findById(id).orElseGet(() -> {
            return null;
        });
        comment.setBoard(board);
        comment.setBoardId(board.getId());

        if(user == null) {
            System.out.println("에러");
        }
        else{
            Integer score = user.getScore();
            Integer score1;
            if (score == null){
                score1 = 2;
            } else {
                score1 = score + 2;
            }
            user.setScore(score1);
            comment.setUser(user);
            comment.setUserName(user.getName());
        }

        commentAPIRepository.save(comment);

    }

    public Comment reWrite(Comment newComment, Integer id) {
        return commentAPIRepository.findByBoardId(id)
                .map(comment -> {
                    comment.setContent(newComment.getContent());
                    return commentAPIRepository.save(comment);
                })
                .orElseGet(() -> {
                    newComment.setId(id);
                    return commentAPIRepository.save(newComment);
                });
    }
}
