<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: auto;
        }

        th, td {
            border: 1px solid #999;
            padding: 8px 12px;
            text-align: center;
        }

        th {
            background-color: #f0f0f0;
        }

        caption {
            font-size: 1.5em;
            margin: 20px;
        }
    </style>
</head>
<body>
    <%-- req, resp 객체가 전달된 상태 --%>
<%-- 속성(attribute, property, item) : 세부적인 항목을 의미 --%>
<table>
    <caption>게시판 목록</caption>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글내용</th>
        <th>작성자</th>
    </tr>

    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.no}</td> <%-- item.getNo()함. --%>
            <td>${item.title}</td>
            <td>${item.content}</td>
            <td>${item.writer}</td>
        </tr>
    </c:forEach>

    <c:if test="${empty list}">
        <tr>
            <td colspan="4">게시글이 없습니다.</td>
        </tr>
    </c:if>
</table>
</body>
</html>












