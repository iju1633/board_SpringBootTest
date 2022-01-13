package com.itbanker.board.repository;

import com.itbanker.board.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository { // controller, service, repository 에서 가장 먼저 repository 를 먼저 작성함
    //  뒷단 부터 개발하는 것이 나중에 Autowired 붙이기 편해짐!(이해)
    // db 연동하는 코드...!
    // JPA
    static public ArrayList<UserDTO> users;

    static {
        users = new ArrayList<>();
        users.add(new UserDTO("Garfield", "test1", "1234"));
        users.add(new UserDTO("Eric", "test2", "5678"));
        users.add(new UserDTO("Tom", "test3", "9101"));
    }

    public UserDTO insertUser(UserDTO user) {
        users.add(user);
        return user;
    }

    public List<UserDTO> getAllUsers() {
        return users;
    }

    public UserDTO getUserByUserId(String userId) {
        return users.stream()
                .filter(userDTO -> userDTO.getUserId().equals(userId)) // UserDTO를 모두 불러와서 userId가 같은 것을 가져오게끔 필터를 거는 작업
                .findAny()
                .orElse(new UserDTO("", "", "")); // 일치하는 게 없다면 빈 것 return
    }

    public void updateUserPw(String userId, UserDTO user) { // userId가 일치하는 객체 가져와서 비밀번호 갱신함
        users.stream()
                .filter(userDTO -> userDTO.getUserId().equals(userId))
                .findAny()
                .orElse(new UserDTO("", "", ""))
                .setUserPw(user.getUserPw());
    }

    public void deleteUser(String userId) { // userId가 일치하는 객체 가져와서 제거함
        users.removeIf(userDTO -> userDTO.getUserId().equals(userId));
    }
}
