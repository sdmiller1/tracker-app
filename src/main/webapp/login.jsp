<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login</title>
    <c:import url="templates/head.jsp" />
</head>
<body>
<c:import url="templates/nav.jsp" />
<main class="container-md">
    <section class="row mt-3 justify-content-center">
        <h1 class="col-12 text-center">
            Login
        </h1>
        <form class="col-12 col-md-6 needs-validation" novalidate action="j_security_check" method="post">
            <section id="formAlertBox">
            </section>
            <fieldset class="form-group">
                <label for="username">Username:</label>
                <input type="text" name="j_username" id="username" class="form-control" placeholder="Username" required>
                <div class="invalid-feedback">
                    Please enter your username.
                </div>
            </fieldset>
            <fieldset class="form-group">
                <label for="password">Password:</label>
                <input type="password" name="j_password" id="password" class="form-control" placeholder="Password" required>
                <div class="invalid-feedback">
                    Please enter your password.
                </div>
            </fieldset>

            <button class="btn btn-primary" type="submit">
                Login
            </button>
            <a href="signup" class="btn btn-secondary float-right">
                Create an account
            </a>
        </form>
    </section>
</main>

<c:import url="templates/alertbox.jsp" />

<c:import url="templates/formValidationScript.jsp" />
</body>
</html>
