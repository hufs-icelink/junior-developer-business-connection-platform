package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardEntity", nullable = false)
    private Board board;

    @Column(nullable = false)
    private Integer boardId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntity", nullable = false)
    private User user;

    @Column(nullable = false)
    private String userName; //게시글을 작성한 유저의 이름을 위한 애트리뷰트

    @Column(nullable = false)
    private String userId; //작성자의 아이디
    @Lob
    @Column(nullable = false)
    private String content;
    @ColumnDefault("0")
    private Integer likeNum;

    @CreatedDate
    private Timestamp date;
}