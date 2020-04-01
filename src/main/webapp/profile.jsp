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

    <section class="movie-grouping">
        <div class="movie-grouping-heading">
            <h2>Your Favorite Movies</h2>
            <a href="#">See More</a>
        </div>

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
</main>
</body>
