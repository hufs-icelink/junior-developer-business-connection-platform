package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
@Entity //entity 정의 --> 스프링으로 db 테일블 생성
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, length = 200)
    private String title;
    @Lob
    private String content;
    @Column(nullable = false, length = 200)
    private String link;
    @ColumnDefault("0")
    private Integer like_num;
    @ColumnDefault("0")
    private Integer view_num;
    @Column(nullable = false, length = 100)
    private String job;

    @Column(length = 200)
    private String skill_1;
    @Column(length = 200)
    private String skill_2;
    @Column(length = 200)
    private String skill_3;
    @CreatedDate
    private LocalDateTime date;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Board_comp> board_compList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Board_part> board_partList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();
}