<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body bgcolor="yellow">
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<hr>
컨트롤러로 부터 전달받은 name : ${name}<br>
컨트롤러로 부터 전달받은 age : ${age}<br>
<%-- Expresssion(출력) Language(EL) : 속성 지정된 것만 출력시 --%>
</body>
</html>