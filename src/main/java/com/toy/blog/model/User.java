package com.toy.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // User 클래스가 MySQL에 테이블이 생성됨
// @DynamicInsert // null 필드의 insert 제외
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따라감(auto_increment)
    private int id; // 시퀀스(oracle), auto_increment(mysql)

    @Column(nullable = false, length = 30, unique = true) // unique : 중복값 방지
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // DB는 RoleType이 없어 Mysql에 string타입이란 걸 알려줘야 함
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 시간 자동입력
    private Timestamp createDate;
}
