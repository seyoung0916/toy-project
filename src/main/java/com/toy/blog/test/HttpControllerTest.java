package com.toy.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자 요청 -> 응답 (data)
@RestController
public class HttpControllerTest {

    @GetMapping("/http/get")
    public String getTest(Member m) {
        return "get 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
    }

    // 인터넷 브라우저 요청은 get만 가능
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
    }

    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
