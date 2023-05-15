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
    @JoinColumn(name = "userId", nullable = false)
    private User user;
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

}