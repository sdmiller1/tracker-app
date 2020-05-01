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

<script>
// Script to use bootstrap form validation
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>
