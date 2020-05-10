<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Your Profile</title>

    <c:import url="templates/setJavascriptUsername.jsp" />

    <c:import url="templates/head.jsp" />
</head>
<body>
<c:import url="templates/nav.jsp" />

<main class="container-md">
    <c:if test="${user != null}">
    <section class="row mt-3">
        <div class="col-lg-3 col-md-4 col-6">
            <img src="images/default_profile.jpg" alt="Profile Picture" class="rounded-lg w-100">
        </div>
        <div class="col-lg-9 col-md-8 col-6">
            <h2>${user.firstName} ${user.lastName} <small>(${user.username})</small></h2>
            <p class="">
            </p>
        </div>
    </section>
    </c:if>

    <c:forEach var="collection" items="${collections}">
    <c:set var="collectionId" value="${collection.id}" scope="request"/>
    <div class="row">
        <h2 class="col-sm-6 text-dark mt-2">
            ${collection.collectionName}
        </h2>
        <div class="col-sm-6 text-right mt-2 d-flex align-items-center justify-content-end">
            <a href="collection?id=${collection.id}" class="btn btn-link btn-lg">See More</a>
        </div>
    </div>

    <div class="row">
        <c:if test="${collection.movies.size() > 4}">
            <c:set var="moviesToDisplay" value="${collection.movies.subList(0, 4)}" scope="request"/>
        </c:if>
        <c:if test="${collection.movies.size() <= 4}">
            <c:set var="moviesToDisplay" value="${collection.movies}" scope="request"/>
        </c:if>
        <c:import url="templates/displayMovies.jsp" />
    </div>
    </c:forEach>
</main>

<c:import url="templates/formValidationScript.jsp" />
</body>
</html>