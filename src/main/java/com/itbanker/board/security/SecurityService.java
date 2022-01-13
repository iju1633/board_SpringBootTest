package com.itbanker.board.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    // 실제로는 이렇게 비밀키를 만들면 안됨!
    private static final String SECRET_KEY = "aslsdisdjfiasojfoiasjfoaskfmsdklfaskldfajrhwqieobfwqegwoqifbiwoqbfiwoqfeqsd";

    // 로그인 서비스 던질 때 같이 ~~
    // 토큰을 구현하는 메서드!
    public String createToken(String subject, long expTime) {
        if(expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야합니다.");
        }

        // 서명 알고리즘을 구현하기 위해 HS256이라는 알고리즘 선택함
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // String 형태의 key 를 byte 로 전환해줌
        // implementation 'javax.xml.bind:jaxb-api' 에서 DatatypeConverter 를 활용
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        // 암호화 키를 만듦
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        // 만든 키를 return
        return Jwts.builder()
                .setSubject(subject) // user 의 id 값을 subject 로 받고, 비밀번호로 SECRET_KEY 를 만드는 데 사용(강사님 생각)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime)) // 실제로는 헤더 값 등 다양한 정보를 넣을 수 있음
                .compact();
    }

    // 토큰 검증하는 메서드를 boolean ~~
    // 원래는 내부에서 인증되어있는 토큰인 지 파악하도록 작성함
    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder() // Claims 는 pay load 안에 담겨있는 정보
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)) // 키를 setting
                .build()
                .parseClaimsJws(token) // token 을 넣어서 풀어줌
                .getBody(); // 풀어준 값을 가져옴

        return claims.getSubject(); // 풀어준 claims 안에 있는 정보 중, subject 만 가져옴
    }
}
