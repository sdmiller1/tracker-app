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
    <title>Login</title>
</head>
<body>
    <form action="j_security check" method="post">
        <label>
            Username:
            <input type="text" name="j_username">
        </label>
        <label>
            Password:
            <input type="password" name="j_password">
        </label>
        <button type="submit">Login</button>
    </form>
</body>
</html>
