<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/2/20
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <table>
        <tr><th>First Name</th><th>Last Name</th></tr>
        <c:forEach var="user" items="${results}">
            <tr><td>${user.firstName}</td><td>${user.lastName}</td></tr>
        </c:forEach>
    </table>
</body>
</html>