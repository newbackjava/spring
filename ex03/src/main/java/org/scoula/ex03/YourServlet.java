package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//컨테이너에 등록할 때 방법 2가지 --> 1)어노테이션, 2) web.xml
//@WebServlet(name = "you", value = "/you")
public class YourServlet extends HttpServlet {

    //서블릿객체는 최초 호출했을 때 생성됨. --> 객체는 하나만 만들어서 계속 사용함.
    //객체생성시 초기화는 1번, 소멸시 1번 호출해서 기능을 정의할 수 있음.


    @Override
    public void init() throws ServletException {
        System.out.println("YourServlet init-한번만 호출됨");
    }

    @Override
    public void destroy() {
        System.out.println("YourServlet destroy-한번만 호출됨");
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("YourServlet doPost");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("YourServlet doGet"); //서버쪽 컴퓨터의 프린트내용!
        //제어하는 부분 --> db연동해야하는 경우 (DAO를 써서 결과 받아오는 코드)
        
        //응답해보자!!! --> html로 생성해야함.
        //컨터이너에 의해 만들어진 req, resp객체를 이용해서
        //요청들어온 정보를 추출할 수도 있고
        //처리결과를 html로 만들어서 응답할 수도 있음.
        resp.setContentType("text/html;charset=utf-8"); //보낼 데이터 타입 지정
        //html문자를 만들어줄 객체가 필요함. PrintWriter 필요!
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body bgcolor='yellow'>");
        out.println("<h1>서버에서 브라우저로 전송되는 내용</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
