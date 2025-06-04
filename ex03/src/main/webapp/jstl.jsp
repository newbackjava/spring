
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
  <title>JSTL Example</title>
</head>
<body>
<h2>조건문 예제</h2>
<c:if test="${5 > 2}">
  <p>5는 2보다 큽니다.</p>
</c:if>

<h2>반복문 예제</h2>
<ul>
  <c:forEach var="i" begin="1" end="5">
    <li>번호 ${i}</li>
  </c:forEach>
</ul>

<hr>
<%
  Date now = new Date();
  pageContext.setAttribute("now", now); //page내 속성이 필요한 경우
  pageContext.setAttribute("price", 10000000);
%>
<!-- – 날짜 formatting -->
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />


<!-- 숫자 formatting -->
<fmt:formatNumber value="${price}" type="currency" />
</body>
</html>

