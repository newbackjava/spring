<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
<h1>getParameter 실습</h1>
<form action="/login2" method="get">

  <!-- method="post"로 변경해보자 -->
  <!-- <form action="login" method="post"> -->

  <fieldset>
    <legend>로그인 폼</legend>
    <ul  style="list-style: none">
      <li>
        <label for="userid">아이디</label>
        <input type="text" id="userid" name="userid" value="apple">
      </li>
      <li>
        <label for="passwd">비밀번호</label>
        <input type="password" id="passwd" name="passwd" value="나는 한글이다">
      </li>
      <li><input type="submit" value="전송"></li>
    </ul>
  </fieldset>
</form>
</body>
</html>
