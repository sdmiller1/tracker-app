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
    <h1>${collectionName}</h1>
    <section class="movie-grouping">
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