<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<%--    TODO: make this title dynamic to the movie--%>
    <title>Browse Movies</title>
    <c:import url="templates/head.jsp" />
</head>
<body>
<c:import url="templates/nav.jsp" />


<main>
    <c:if test="${movie != null}">
    <section class="movie-page">
        <img src="${movie.image}" alt="${movie.title}">
        <div class="movie-page-info">
            <h2>${movie.title} (${movie.releaseDate})</h2>
            <p>${movie.plot}</p>
            <button>add to Collection</button>
        </div>
    </section>
    </c:if>

    <c:if test="${searchResults != null}">
    <section class="movie-grouping">
        <div class="movie-grouping-heading">
            <h2>Search results for: ${request.getParamater("search")}</h2>
            <a href="#">See More</a>
        </div>

        <div class="movie-list">
            <c:forEach var="movie" items="${searchResults}">
                <div class="movie">
                    <a href="movie?id=${movie.imdbId}">
                        <img src="${movie.image}" alt="${movie.title}">
                    </a>
                    <button><i class="fa fa-plus"></i></button>
                </div>
            </c:forEach>
        </div>
    </section>
    </c:if>


    <section class="movie-grouping">
        <div class="movie-grouping-heading">
            <h2>Other Movies</h2>
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
