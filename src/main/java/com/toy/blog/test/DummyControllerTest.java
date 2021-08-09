package com.toy.blog.test;

import com.toy.blog.model.RoleType;
import com.toy.blog.model.User;
import com.toy.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패했습니다. 해당 id는 DB에 없습니다.";
        }

        return "삭제되었습니다. id: " + id;
    }

    // 주소가 같아도 요청 방식이 달라서 알아서 구분됨
    // email, password
    // json 데이터를 전달받을 때 @requestbody 사용
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        // json 데이터를 요청 -> spring boot가 java object로 변환해 받음
        // jackson 라이브러리가 담당
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());

        // 기존받은 데이터를 바로 덮어씌우면 없는 데이터가 새로 null로 덮어씌우므로 해당 과정 필요
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패했습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        // 이제 user에는 null 데이터가 없음
        // save는 id를 전달하지 않으면 insert, 전달하면 해당 id에 대한 데이터가 있을 경우 update하고 없을 경우 insert
        // userRepository.save(user);
        return user; // 업데이트된 user 정보 반환
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터 리턴
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // id 역순으로 정렬
        Page<User> pagingUser = userRepository.findAll(pageable);

        // paginUser에 필요한 정보가 있을 수 있으므로 변수에 저장하고 따로 뽑음
        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파라미터를 전달받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // null이 리턴되는 것을 방지해 User 객체를 감싸서 Optional로 반환해서 가져옴
        User user = userRepository.findById(id).orElseThrow(() -> {
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