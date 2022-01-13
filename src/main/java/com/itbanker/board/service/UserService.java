package com.itbanker.board.service;

import com.itbanker.board.dto.UserDTO;
import com.itbanker.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService { // repository 에서 작성한 CRUD를 여기에서도 작성!

    @Autowired // 의존성을 주입하는 것임 -> service와 repository 계층 연결! // 앞서 계층끼리만 통신해야한다고 언급
    // Spring의 생명 주기는 한 번 꼭 공부해보시라고 말씀하심!
    UserRepository userRepository;

    public UserDTO insertUser(UserDTO user) {
        return userRepository.insertUser(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserDTO getUserByUserId(String userId) {
        return userRepository.getUserByUserId(userId);
    }

    public void updateUserPw(String userId, UserDTO user) {
        userRepository.updateUserPw(userId, user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }


}
