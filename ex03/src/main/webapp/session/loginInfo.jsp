<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html>
    <body>
        <h1>로그인 정보 보기</h1>
        <%
            //스크립트릿
            String id = (String)session.getAttribute("userid");
            if(id == null){
                response.sendRedirect("loginForm.html");
            }else{
                String pw = (String)session.getAttribute("password");
                out.println("사용자 id >> " + id + "<br>");
                out.println("사용자 pw >> " + pw + "<br>");
            }
        %>
    사용자 id >> <%= id %> <br> <%-- 표현식(출력), expression --%>
    <a href="logout.jsp">로그아웃 화면으로</a>
    </body>
</html>