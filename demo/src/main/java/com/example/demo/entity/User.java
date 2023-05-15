package com.example.demo.entity;

import com.example.demo.model.RoleType;
import com.example.demo.model.SexType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@Entity //entity 정의 --> 스프링으로 db 테일블 생성
public class User {
    @Id // pk 지정
    private String id;
    @Column(nullable = false, length = 100)
    private String userPassword;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String mail;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexType sex; // 남자,여자로 도메인 설정
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enterprise,Member,ADMIN 로 도메인 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partBoard")
    private Board board;
    @Column
    private String pictureName;
    @Column
    private String picturePath;
    @Column
    private Integer age;
    @Column(length = 200)
    private String address;
    @Column
    private Integer pay;

    @Column(length = 200)
    private String firstSkill;
    @Column(length = 200)
    private String secondSkill;
    @Column(length = 200)
    private String thirdSkill;
    @Column
    private Integer univerGrade;
    @Column(length = 500)
    private String job;
    @Column
    @Lob
    private String short_res;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Career> careerList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Certificate> certificateList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Blog> BlogList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Github> GithubList = new ArrayList<>();





}
