package com.itbanker.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "/wise", produces = "application/json; charset=utf8") // produce 속성 없을 때는 숫자로만 결과가 나왔고, 있을 때는 한글은 깨진 상태로 정상 출력됨
// BufferedReader 객체 생성 시, charset 값을 euc-kr로 주면 xml 에서도 한글로 잘 보임!
public class WiseController {

    //  행정학과 과목을 조회하는 실습
    @GetMapping("/search/public_administration")
    public String Subject() {
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("https://wise.uos.ac.kr/uosdoc/api.ApiUcrMjTimeInq.oapi");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=202201667HHP29253"); // 발급받았던 API key
            urlBuilder.append("&" + URLEncoder.encode("year", "UTF-8") + "=2021"); // 2021년
            urlBuilder.append("&" + URLEncoder.encode("term", "UTF-8") + "=A20"); // 2학기
            urlBuilder.append("&" + URLEncoder.encode("deptDiv", "UTF-8") + "=20000"); // 소속구분 : 대학
            urlBuilder.append("&" + URLEncoder.encode("dept", "UTF-8") + "=A201120212"); // 대학 : 정경대학
            urlBuilder.append("&" + URLEncoder.encode("subDept", "UTF-8") + "=A201140214"); // 학부(과) : 행정학과
//            urlBuilder.append("&" + URLEncoder.encode("subjectDiv", "UTF-8") + "=A04 "); // 교과구분 : 전공선택
//            urlBuilder.append("&" + URLEncoder.encode("subjectNo", "UTF-8") + "=12001"); // 교과번호 : 12001
//            urlBuilder.append("&" + URLEncoder.encode("classDiv", "UTF-8") + "=01"); // 분반 : 01
//            urlBuilder.append("&" + URLEncoder.encode("subjectNm", "UTF-8") + "=행정학개론"); // 교과목명 : 행정학개론
//            urlBuilder.append("&" + URLEncoder.encode("etcExc", "UTF-8") + "=Y"); // 기타과목제외 여부(Y/N) : Y

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "EUC-KR"));

            }
            else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }

            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";

    }

}
