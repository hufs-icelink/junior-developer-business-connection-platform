package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardAPIRepositoty;
import com.example.demo.repository.UserAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardAPIService {

    @Autowired
    private BoardAPIRepositoty boardAPIRepositoty;

    @Autowired
    private UserAPIRepository userAPIRepository;


    public void write(Board board){
        board.setDate(LocalDateTime.now());
        User user = userAPIRepository.findByName(board.getUserName()).orElseGet(()-> {
            return null;
        });

        if(user == null) {
            System.out.println("에러");
        }
        else{
            board.setUser(user);
        }

        boardAPIRepositoty.save(board);

    }

    public Board reWrite(Board newBoard, Integer id) {
        return boardAPIRepositoty.findById(id)
                .map(board -> {
                    String title = newBoard.getTitle();
                    String content = newBoard.getContent();
                    String link = newBoard.getLink();
                    String note = newBoard.getNote();
                    if (title != null){
                        board.setTitle(newBoard.getTitle());
                    }
                    if (content != null){
                        board.setContent(newBoard.getContent());
                    }
                    if (link != null) {
                        board.setLink(newBoard.getLink());
                    }
                    if (note != null) {
                        board.setNote(newBoard.getNote());
                    }
                    return boardAPIRepositoty.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardAPIRepositoty.save(newBoard);
                });
    }
}