package com.toy.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량
    private String content; // 썸머노트lib : <html>태그가 섞여 디자인됨

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Board = Many, User = one
    @JoinColumn(name = "userId") // userId 컬럼명
    private User user; // DB는 오브젝트를 저장할 수 없어 FK 사용

    @CreationTimestamp
    private Timestamp createDate;

}