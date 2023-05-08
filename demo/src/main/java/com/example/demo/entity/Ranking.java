package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Entity
@Data
public class Ranking {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MemberEntity memberEntity;
    @ColumnDefault("0")
    private Integer score;
    @Column(nullable = false)
    private Integer ranking;
    @ColumnDefault("0")
    private Integer first_pl;
    @ColumnDefault("0")
    private Integer second_pl;
    @ColumnDefault("0")
    private Integer third_pl;

}