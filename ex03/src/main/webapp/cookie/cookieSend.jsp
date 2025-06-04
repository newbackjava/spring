<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
  <%
    Cookie c1 = new Cookie("name", "honggildong"); //name, value
    Cookie c2 = new Cookie("age", "100");          //String, String
    response.addCookie(c1); //브라우저에게 쿠키를 심으라고 명령함.
    response.addCookie(c2);
%>
<body>
브라우저에 쿠키를 심었음. <a href="cookieGet.jsp">쿠키확인하러 가기</a>
</body>
</html>
