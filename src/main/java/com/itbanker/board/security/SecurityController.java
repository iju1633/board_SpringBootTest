package com.itbanker.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @GetMapping("/create/token") // @RequestParam을 통해 url 에서 subject?=kang 이런 식으로 설정 가능
    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject) { // (key, value) 형태이고 val ue가 json 형식이니까 Object 로 설정
        String token = securityService.createToken(subject, (2*1000*60));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);

        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
        String subject = securityService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);

        return map;
    }
}

// postman 에서 test 진행
// token 의 형태를 보면 ~~~.~~~.~~~ 이렇게 되어 있는데, 첫 번째는 header, 두 번째는 payload(claim),
// 세 번째는 header 와 payload 를 인코딩한 값을 key로 해싱시켜서 만들어낸 서명값에 해당한다.
// jwt.io 라는 사이트에서 암호키 값을 넣어 검증이 가능함!
