<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="main.css">
    <script src="js.js"></script>
    <title>Browse Movies</title>
</head>
<body>
<nav>
    <a href="#">Browse Movies</a>
    <a href="#">My Movies</a>
    <a href="#">Watch Later</a>
</nav>

<main>
    <h1>Browse Movies</h1>
    <section class="movie-grouping">
        <div class="movie-grouping-heading">
            <h2>All Movies</h2>
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
</html>