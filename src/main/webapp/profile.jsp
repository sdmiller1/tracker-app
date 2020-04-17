<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Your Profile</title>
    <c:import url="templates/head.jsp" />
</head>
<body>
<c:import url="templates/nav.jsp" />


<main>
    <section class="profile">
        <img src="" alt="Profile Picture">
        <div class="profile-info">
            <h2>${user.firstName}</h2>
            <p>Favorite Genre: Drama</p>
        </div>
    </section>

    <c:forEach var="collection" items="${collections}">
        <section class="movie-grouping">
            <div class="movie-grouping-heading">
                <h2>Your ${collection.collectionName}</h2>
                <a href="browse?collection=${collection.id}">See More</a>
            </div>

            <c:set var="movies" value="${collection.movies}"/>
            <div class="movie-list">
                <c:forEach var="movie" items="${movies}">
                    <div class="movie">
                        <a href="movie?id=${movie.imdbId}">
                            <img src="${movie.image}" alt="${movie.title}">
                        </a>
                        <button><i class="fa fa-plus"></i></button>
                    </div>
                </c:forEach>
            </div>
        </section>
    </c:forEach>
</main>
</body>
