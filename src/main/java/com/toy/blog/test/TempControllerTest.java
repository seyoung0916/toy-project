package com.toy.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        // src/main/resources/static이 기본 경로
        return "/home.html";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        return "/test";
    }
}
