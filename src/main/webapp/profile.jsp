<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Your Profile</title>
    <c:if test="${user != null}">
        <script>
            let username = "${user.username}";
        </script>
    </c:if>
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
<%--                TODO: remove placeholder text --%>
                placeholder text
            </p>
        </div>
    </section>
    </c:if>

    <c:forEach var="collection" items="${collections}">
    <div class="row">
        <h2 class="col-sm-6 text-dark mt-2">
            ${collection.collectionName}
        </h2>
        <div class="col-sm-6 text-right mt-2 d-flex align-items-center justify-content-end">
            <a href="collection?id=${collection.id}" class="btn btn-link btn-lg">See More</a>
        </div>
    </div>

    <div class="row">
        <c:set var="movies" value="${collection.movies}"/>
        <c:forEach var="movie" items="${movies}">
            <div class="col-6 col-lg-3 my-2">
                <div class="card h-100">
                    <a href="movie?id=${movie.imdbId}">
                        <img class="card-img-top" src="${movie.image}" alt="${movie.title}">
                    </a>
                    <div class="card-body">
                        <a href="movie?id=${movie.imdbId}" class="noLinkStyle"><h2 class="card-title h4">${movie.title}</h2></a>
                        <button type="button" class="btn btn-danger w-100" onclick="removeMovieFromCollection('${movie.imdbId}', '${collection.id}')">
                            Remove
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    </c:forEach>
</main>

<c:import url="templates/formValidationScript.jsp" />
</body>
</html>