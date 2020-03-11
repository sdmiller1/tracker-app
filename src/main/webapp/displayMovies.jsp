<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <title>${title}</title>--%>
    <title>Display Movies</title>
</head>
<body>
<table>
    <tr><th>id</th><th>Title</th></tr>
    <c:forEach var="movie" items="${movies}">
        <tr><td>${movie.id}</td><td>${movie.title}</td></tr>
    </c:forEach>
</table>
</body>
</html>
