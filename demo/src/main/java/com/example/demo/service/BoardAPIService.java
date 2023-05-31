package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardRepositoty;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardAPIService {

    @Autowired
    private BoardRepositoty boardRepositoty;

    @Autowired
    private UserRepository userRepository;


    public void write(Board board){
        board.setDate(LocalDateTime.now());
        User user = userRepository.findByName(board.getUserName()).orElseGet(()-> {
            return null;
        });

        if(user == null) {
            System.out.println("에러");
        }
        else{
            board.setUser(user);
        }

        boardRepositoty.save(board);

    }

    public Board reWrite(Board newBoard, Integer id) {
        return boardRepositoty.findById(id)
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
                    return boardRepositoty.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardRepositoty.save(newBoard);
                });
    }
}