<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2025-06-23
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>member/admin 접근 가능 : member.jsp </h1>
<form action="/security/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="로그아웃"/>
</form
</body>
</html>


