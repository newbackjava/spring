<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Todo 보기</h1>

<div>
    <%-- a href="/todo/list" 상대주소가 생김, 현재 폴더가 주소앞에 붙어서 호출함. --%>
    <a href="list">목록보기</a> |
    <a href="update">수정하기</a>
</div>

<form action="delete" method="POST">
    <input type="submit" value="삭제">
</form>

</body>
</html>

