package com.itbanker.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// lombok을 사용하면 getter setter constructor 쉽게 생성 가능!
@Getter @Setter
@AllArgsConstructor
public class UserDTO { // SpringBoot 내에서 데이터들이 이동하는 단위

    private String userName;
    private String userId;
    private String userPw;
}

// lombok 을 특징인데, 컴파일 시점에서 코드를 생성해주어야 하기에 implementation 말고 annotationProcessor
// 와 compileOnly 를 변경해서 넣으면 됨
// 이렇게 안하고 그냥 implementation 으로 하는 경우, lombok 의 코드를 읽지 못함!

// browser 에서만 테스트할 경우, get 만 test 해볼 수 있으니까, postman 으로 test 해보자!
