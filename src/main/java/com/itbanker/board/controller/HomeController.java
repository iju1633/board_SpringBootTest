package com.itbanker.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
// 스프링에서 비동기 처리를 하는 경우 @RequestBody , @ResponseBody를 사용한다
// @RequestBody 어노테이션과 @ResponseBody 어노테이션이 각각 HTTP요청 바디를 자바객체로 변환하고 자바객체를 다시 HTTP 응답 바디로 변환해준다.

/*
@RequestBody / @ResponseBody 정리

클라이언트에서 서버로 필요한 데이터를 요청하기 위해 JSON 데이터를 요청 본문에 담아서 서버로 보내면,
서버에서는 @RequestBody 어노테이션을 사용하여 HTTP 요청 본문에 담긴 값들을 자바객체로 변환시켜, 객체에 저장한다.

서버에서 클라이언트로 응답 데이터를 전송하기 위해 @ResponseBody 어노테이션을 사용하여 자바 객체를
HTTP 응답 본문의 객체로 변환하여 클라이언트로 전송한다.
 */

@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String test() {
        return "test!!!";
    }
}

// cmd창에서 프로젝트 위치로 cd를 통해 이동한 후, gradlew clean build해서 build한 후에, build파일로 
// 이동한 후, jar파일을 배포! -> java -jar board-0.0.1-SNAPSHOT.jar이라고 치면 됨
// intelliJ의 해서 서버 돌렸을 때랑 같은 결과창 보여줌
