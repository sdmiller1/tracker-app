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
    <section class="movie-page">
        <img src="images/the_martian.jpg" alt="The Martian">
        <div class="movie-page-info">
            <h2>The Martian (2015)</h2>
            <p>Matt Damon on mars</p>
            <button>add to Collection</button>
        </div>
    </section>

    <section class="movie-grouping">
        <div class="movie-grouping-heading">
            <h2>Similar Movies</h2>
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
