package com.toy.blog.repository;

import com.toy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// User 테이블을 관리하는 repo, PK는 Integer
// DAO, 자동으로 빈 등록
@Repository // 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
}
