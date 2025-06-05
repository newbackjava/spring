package org.scoula.ex03.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//Todo domain에 대해서만 컨트롤할 수 있는 컨트롤러를 만들자.
//Board, Member, Memo, Product 등 일반적으로
//domain(큰 메뉴)별 해당 테이블이 1개 이상 존재하므로
//테이블별로 컨트롤러를 만들어두는 편(domain별)
public class TodoController {

    //webapp아래에는 정적인 파일(css, js, img등만 넣어두고)
    //모든 jsp파일은 컨트롤러를 거쳐서 호출되도록 하는 경우도 있음.
    //컨트롤러를 통해서 호출하면 컨트롤러에 모든 흐름이 보여 제어하기 쉬움.

    //해야할일 목록 가지고 오기
    //프론트컨트롤러가 불러요.!! 리턴도 프론트컨트롤러에게 view이름을 리턴
    public String getList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> list = Arrays.asList("Todo 1", "Todo 2", "Todo 3");

        req.setAttribute("todoList", list);

        System.out.println("GET /todo/list");

        return "todo/list"; //프론트 컨트롤러에게 view이름을 리턴함.
        //프론트컨트롤러가 이 view이름을 이용해서
        // /views/todo/list.jsp붙여서 호출함.(forward는 프론트컨트롤러가 다함.)
    }

    //상세보기
    public String getView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("info", "나는 상세정보");
        System.out.println("GET /todo/view");
        return "todo/view";
    }

    //입력하는 화면 불러오기
    public String getCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/create");
        return "todo/create";
    }

    //입력하는 화면에서 입력 데이터를 입력하셔 서버로 보내어,
    //db처리 후 list로 목록을 보고 싶은 경우
    public String postCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        System.out.println("POST /todo/create");

        //create작업 후, list로 넘어가고 싶은 경우
        //list결과를 가지고 jsp에 넣어야하므로, 컨트롤러의 주소를 불러야함.
        //결과를 담지않고, 완전히 다른 화면을 호출하는 경우 redirect로 호출
        return "redirect:/todo/list";
    }

    //수정하는 화면 불러오기
    public String getUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/update");
        return "todo/update";
    }

    //수정하는 화면에서 입력하여 수정처리 요청
    //수정처리후 list로 화면 넘어감.
    public String postUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/update");
        return "redirect:/todo/list";
    }

    //삭제버튼을 누르면 삭제처리 요청
    //삭제처리후 list로 화면 넘어감
    public String postDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/delete");
        return "redirect:/todo/list";
    }
}