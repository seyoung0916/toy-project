package com.toy.blog.test;

import com.toy.blog.model.RoleType;
import com.toy.blog.model.User;
import com.toy.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    // {id} 주소로 파라미터를 전달받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // null이 리턴되는 것을 방지해 User 객체를 감싸서 Optional로 반환해서 가져옴
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });

        // 요청: 웹브라우저, user 객체: 자바 object
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
        // 스프링부트의 MessageConverter가 응답 시에 자동 작동
        // 만약 자바 object를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해
        // user object를 json으로 변환해 브라우저에게 전달
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}