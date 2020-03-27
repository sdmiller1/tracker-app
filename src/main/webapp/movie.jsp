<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Browse Movies</title>
    <c:import url="templates/head.jsp" />
</head>
<body>
<c:import url="templates/nav.jsp" />


<main>
    <c:forEach var="movie" items="${searchResults}">
    <section class="movie-page">
        <img src="${movie.image}" alt="${movie.title}">
        <div class="movie-page-info">
            <h2>${movie.title} (${movie.releaseDate})</h2>
            <p>${movie.plot}</p>
            <button>add to Collection</button>
        </div>
    </section>
    </c:forEach>

    <section class="movie-grouping">
        <div class="movie-grouping-heading">
            <h2>Other Movies</h2>
            <a href="#">See More</a>
        </div>

        <div class="movie-list">
            <c:forEach var="movie" items="${movies}">
                <div class="movie">
                    <img src="${movie.image}" alt="${movie.title}">
                    <button><i class="fa fa-plus"></i></button>
                </div>
            </c:forEach>
        </div>
    </section>
</main>
</body>
