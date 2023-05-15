package com.example.demo.entity;

import com.example.demo.model.BoardType;
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
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @Column(nullable = false, length = 200)
    private String title;
    @Lob
    private String content;
    @Column(nullable = false, length = 200)
    private String link;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;
    @ColumnDefault("0")
    private Integer likeNum;
    @ColumnDefault("0")
    private Integer viewNum;
    @Column(nullable = false, length = 100)
    private String job;

    @Column(length = 200)
    private String firstSkill;
    @Column(length = 200)
    private String secondSkill;
    @Column(length = 200)
    private String thirdSkill;
    @CreatedDate
    private LocalDateTime date;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<User> UsersList = new ArrayList<>();


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();
}