package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login2_")
public class LoginServlet2_ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 컨트롤, 전달된 데이터 추출, db처리기능(DAO호출)
        //userid=apple&passwd=나는 한글이다, 파라메터
        String userid = req.getParameter("userid"); //apple, 전달받은 데이터는 모두 string으로 인식
        //일단 파라메터 이름과 동일한 변수를 만들어서 저장해두는 편!
        String passwd = req.getParameter("passwd"); //한글

        //2. 응답하는 html생성
        //보낼 때 한글을 utf-8로 인코딩하고 싶으면 설정해야함.
        //http의 body로 전달되는 데이터는 한글 인코딩을 지정해주어야함.
        req.setAttribute("userid2", userid);
        req.setAttribute("passwd", passwd);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
