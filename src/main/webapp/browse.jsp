<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Browse Movies</title>

    <c:import url="templates/setJavascriptUsername.jsp" />

    <c:import url="templates/head.jsp" />
</head>
<body>
<c:import url="templates/nav.jsp" />

<main class="container-md">
    <div class="row">
        <h1 class="col-sm-12 mt-2 text-center text-dark">
            ${collectionName}
        </h1>
    </div>
    <div class="row">
        <c:set var="moviesToDisplay" value="${movies}" scope="request"/>
        <c:import url="templates/displayMovies.jsp" />
    </div>
</main>

<c:import url="templates/collectionModal.jsp" />

<c:import url="templates/alertbox.jsp" />

<c:import url="templates/formValidationScript.jsp" />
</body>
</html>