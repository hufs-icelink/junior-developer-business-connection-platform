package com.example.demo.entity;

import com.example.demo.model.BoardType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntity", nullable = false)
    private User user; //게시글을 작성한 유저 객체를 위한 애트리뷰트 (외래키 역할)
    @Column(nullable = false)
    private String userName; //게시글을 작성한 유저의 이름을 위한 애트리뷰트
    @Column(nullable = false, length = 200)
    private String title;
    @Lob
    @Column
    private String content;
    @Column(length = 200)
    private String link;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;
    @ColumnDefault("0")
    private Integer likeNum;
    @ColumnDefault("0")
    private Integer viewNum;
    @Column
    @Lob
    private String note;

    @CreatedDate
    private LocalDateTime date;

    @OneToMany(mappedBy = "Partboard", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<User> UsersList = new ArrayList<>();


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();
}