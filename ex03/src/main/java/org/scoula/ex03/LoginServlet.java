package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        //html같은 문자로 이루어진 문서를 만들기 위해, 텍스트로 보낼 문자들을 생성할 때 PrintWriter객체 필요
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>로그인 성공!!!</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 컨트롤, 전달된 데이터 추출, db처리기능(DAO호출)
        //http의 body로 전달됨. --> 한글 인코딩 utf-8로 설정해야함.
        //req.setCharacterEncoding("UTF-8"); //한글전송시 매번 모든 서블릿에 넣어주어야함.
        //서블릿이 받기전 한글로 설정하는 부분은 필터를 낄 수 있음. 그럼 서블릿에서 생략가능!
        String userid = req.getParameter("userid"); //apple, 전달받은 데이터는 모두 string으로 인식
        //일단 파라메터 이름과 동일한 변수를 만들어서 저장해두는 편!
        String passwd = req.getParameter("passwd"); //한글

        //2. 응답하는 html생성
        //보낼 때 한글을 utf-8로 인코딩하고 싶으면 설정해야함.
        //http의 body로 전달되는 데이터는 한글 인코딩을 지정해주어야함.
        //resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //html같은 문자로 이루어진 문서를 만들기 위해, 텍스트로 보낼 문자들을 생성할 때 PrintWriter객체 필요
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>로그인 성공!!!>> " + passwd + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
