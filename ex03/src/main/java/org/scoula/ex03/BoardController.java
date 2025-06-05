package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/jstl/list")
public class BoardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 클라이언트로부터 전달된 데이터가 있으면 서블릿에서 받아서 일단 저장 --> 생략
        //2. board 리스트를 dao.getList()를 호출해서 가지고 오자.
        List<BoardVO> list = new BoardDAO().getList();
        System.out.println(list.size());

        //3. jsp파일에 출력할 데이터 항목을 설정하자.!
        req.setAttribute("list", list);
        //value는 Object타입으로 형변환되어 들어감.

        //4. 어떤 jsp에 출력을 분배(할당)할지 결정
        //5. jsp파일을 부르자.(forward, 앞으로!)
        req.getRequestDispatcher("/jstl/result.jsp").forward(req, resp);
    }
}
