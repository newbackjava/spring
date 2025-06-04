package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/utf8.do")
public class Utf8Servlet_ extends HttpServlet {
    private String encoding;

    public Utf8Servlet_(){
        System.out.println("Utf8Servlet 객체 생성");
    }

    public void  doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request.setCharacterEncoding(encoding);
        String name = request.getParameter("name");
        // response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("<html><body>");
        response.getWriter().write("이름: " + name);
        response.getWriter().write("</body></html>");
    }
}


