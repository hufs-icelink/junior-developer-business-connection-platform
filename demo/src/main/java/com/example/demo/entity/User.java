package com.example.demo.entity;

import com.example.demo.model.RoleType;
import com.example.demo.model.SexType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity //entity 정의 --> 스프링으로 db 테일블 생성
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //순환 참조를 해결하기 위한 어노케이션
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

    @Column(length = 200)
    private String githubId;

    @Column(length = 200)
    private String firstBlogId;

    @Column(length = 200)
    private String secondBlogId;

    @Column(length = 200)
    private String thirdBlogId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BoardEntity") //참여중인 게시글 객체를 위한 애트리뷰트
    private Board Partboard;
    @Column
    private String PartBoardTitle;
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

    @ColumnDefault("0")
    private Integer score;
    @Column(nullable = false)
    private Integer ranking;
    @ColumnDefault("0")
    private Integer firstPl;
    @ColumnDefault("0")
    private Integer secondPl;
    @ColumnDefault("0")
    private Integer thirdPl;
    @Column
    @Lob
    private String shortRes;

    private User(String id) {this.id = id;
    }

    @JsonCreator
    public static User create(@JsonProperty("id") String id) {
        return new User(id);
    }


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
