<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Sign Up</title>
    <c:import url="templates/head.jsp" />
</head>
<body>
<c:import url="templates/nav.jsp" />
<main class="container-md">
    <section class="row mt-3 justify-content-center">
        <h1 class="col-12 text-center">
            Create an Account
        </h1>
        <form class="col-12 col-md-6 needs-validation" novalidate action="signup" method="post">
            <section id="formAlertBox">
            </section>
            <fieldset class="form-group">
                <label for="username">Username:</label>
                <input type="text" name="j_username" id="username" class="form-control" placeholder="Username" required value="${username}">
                <div class="invalid-feedback">
                    Please choose a different username.
                </div>
            </fieldset>
            <fieldset class="form-group">
                <label for="password">Password:</label>
                <input type="password" name="j_password" id="password" class="form-control" placeholder="Password" required>
                <div class="invalid-feedback">
                    Please enter a password.
                </div>
            </fieldset>
            <fieldset class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" name="firstName" id="firstName" class="form-control" placeholder="John" required value="${firstName}">
                <div class="invalid-feedback">
                    Please enter your first name.
                </div>
            </fieldset>
            <fieldset class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Smith" required value="${lastName}">
                <div class="invalid-feedback">
                    Please enter your last name.
                </div>
            </fieldset>
            <fieldset class="form-group">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" name="dvd" id="dvd">
                    <label for="dvd" class="custom-control-label">I have a DVD Player</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" name="bluray" id="bluray">
                    <label for="bluray" class="custom-control-label">I have a Blu-ray Player</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" name="4k" id="4k">
                    <label for="4k" class="custom-control-label">I have a 4K Player</label>
                </div>
            </fieldset>
            <button class="btn btn-primary" type="submit">
                Sign Up
            </button>
            <button class="btn btn-danger" type="reset">
                Reset
            </button>
        </form>
    </section>
</main>

<c:import url="templates/alertbox.jsp" />

<c:if test="${errorMessage != null}">
    <script>
        formErrorMessage("${errorMessage}");
    </script>
</c:if>

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
