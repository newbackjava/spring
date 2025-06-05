package org.scoula.ex03.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

    //요청하나당 함수 하나임.
    // /요청들어왔을 때 (첫 페이지) 호출할 함수를 정의함.
    // 도메인별 컨트롤러의 메서드의 리턴은 결과를 담을 view파일이름이 되어야함.
    public String getIndex(HttpServletRequest request, HttpServletResponse response){
        //함수별 처리 내용을 여기에 써주세요.!!!!
        //첫페이지라 아무것도 안써도 됨.
        return "index"; //view파일 이름만 리턴!!
    }
}
