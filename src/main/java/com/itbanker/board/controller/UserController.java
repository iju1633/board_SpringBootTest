package com.itbanker.board.controller;

import com.itbanker.board.dto.UserDTO;
import com.itbanker.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/users") // URL을 설계하는 것은 현업에서 매우 중요한 작업! 신중하게 미리 고민해서 짤 것
public class UserController { // 추가적인 작업 필요 -> Mapping과 RequestBody, PathVariable 어노테이션 의미!

    @Autowired
    UserService userService;
    //CRUD
    // C -> POST
    // R -> GET
    // U -> PUT
    // D -> DELETE

    @PostMapping("")
    public UserDTO insertUser(@RequestBody UserDTO user) { // requestBody 를 userDTO 형태로 받아올거라는 의미
        return userService.insertUser(user);
    }
    // 같은 URL이지만 다른 메서드를 활용해서 다양한 서비스를 만들 수 있는 것이 Restful 서비스의 또 다른 특징임!

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUserByUserId(@PathVariable String userId) { // URL의 userId 값이 매개변수인 userId로 매핑된다는 의미!
        return userService.getUserByUserId(userId);
    }

    @PutMapping("/{userId}")
    public void updateUserPw(@PathVariable String userId, @RequestBody UserDTO user) {
        userService.updateUserPw(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

}
