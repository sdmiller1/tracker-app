<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Browse Movies</title>
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
    <div class="row">
        <h1 class="col-sm-12 text-center">
            ${collectionName}
        </h1>
    </div>
    <div class="row">
    <c:forEach var="movie" items="${movies}">
        <div class="col-6 col-sm-4 col-lg-3 my-2">
            <div class="card">
                <a href="#">
                    <img class="card-img-top" src="${movie.image}" alt="${movie.title}">
                </a>
                <div class="card-body">
                    <a href="#" class="noLinkStyle"><h2 class="card-title h4">${movie.title}</h2></a>
                    <button type="button" class="btn btn-primary" onclick="console.log('${movie.title}')" data-toggle="modal" data-target="#collectionModal">Add To Collection</button>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</main>

<section class="modal" id="collectionModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add Movie To Collection</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <ul class="list-group" id="collectionList">
                <c:if test="${user} == null">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        You need to log in to save movies
                    </li>
                </c:if>
                <c:forEach var="collection" items="${user.getCollections()}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        ${collection.collectionName}
                        <button class="btn btn-primary addToCollectionButton" type="button" onclick="console.log('waaaaaaaaa')">Add</button>
                    </li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</section>

<c:import url="templates/alertbox.jsp" />
</body>
</html>