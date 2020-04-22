<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/6/20
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
</head>
<body>
    <form action="signup" method="post">
        <label>
            First Name:
            <input type="text" name="firstName">
        </label>
        <label>
            Last Name:
            <input type="text" name="lastName">
        </label>
        <label>
            Username:
            <input type="text" name="username">
        </label>
        <label>
            Password:
            <input type="password" name="password">
        </label>
        <button type="submit">Login</button>
    </form>
</body>
</html>
