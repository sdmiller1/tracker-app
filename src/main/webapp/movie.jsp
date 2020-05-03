<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<%--    TODO: make this title dynamic to the movie--%>
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
    <c:if test="${movie != null}">
        <section class="row mt-3">
            <div class="col-lg-3 col-md-4 col-6">
                <img src="${movie.image}" alt="${movie.title}" class="rounded-lg w-100">
            </div>
            <div class="col-lg-9 col-md-8 col-6">
                <h2>${movie.title} <small>${movie.releaseDate}</small></h2>
                <p class="">
                        ${movie.plot}
                </p>
                <button type="button" class="btn btn-primary" onclick="displayCollectionModal('${movie.imdbId}')" data-toggle="modal" data-target="#collectionModal">
                    Add To Collection
                </button>
            </div>
        </section>
    </c:if>

    <c:if test="${searchResults != null}">
        <div class="row">
            <h2 class="col-sm-6 text-dark mt-2">
                Search results for: ${request.getParamater("search")}
            </h2>
            <div class="col-sm-6 text-right mt-2 d-flex align-items-center justify-content-end">
                <a href="#" class="btn btn-link btn-lg">See More</a>
            </div>
        </div>

        <div class="row">
            <c:forEach var="movie" items="${searchResults}">
                <div class="col-6 col-sm-4 col-lg-3 my-2">
                    <div class="card h-100">
                        <a href="movie?id=${movie.imdbId}">
                            <img class="card-img-top" src="${movie.image}" alt="${movie.title}">
                        </a>
                        <div class="card-body">
                            <a href="movie?id=${movie.imdbId}" class="noLinkStyle"><h2 class="card-title h4">${movie.title}</h2></a>
                            <button type="button" class="btn btn-primary w-100" onclick="displayCollectionModal('${movie.imdbId}')" data-toggle="modal" data-target="#collectionModal">
                                Add To Collection
                            </button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <div class="row">
        <h2 class="col-sm-6 text-dark mt-2">
            Other Movies
        </h2>
        <div class="col-sm-6 text-right mt-2 d-flex align-items-center justify-content-end">
            <a href="browse" class="btn btn-link btn-lg">See More</a>
        </div>
    </div>

    <div class="row">
        <c:forEach var="movie" items="${movies}">
            <div class="col-6 col-sm-4 col-lg-3 my-2">
                <div class="card h-100">
                    <a href="movie?id=${movie.imdbId}">
                        <img class="card-img-top" src="${movie.image}" alt="${movie.title}">
                    </a>
                    <div class="card-body">
                        <a href="movie?id=${movie.imdbId}" class="noLinkStyle"><h2 class="card-title h4">${movie.title}</h2></a>
                        <button type="button" class="btn btn-primary w-100" onclick="displayCollectionModal('${movie.imdbId}')" data-toggle="modal" data-target="#collectionModal">
                            Add To Collection
                        </button>
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
                    <c:if test="${user == null}">
                        <li class="list-group-item text-center">
                            You need to <a href="login.jsp">log in</a> to save movies
                        </li>
                    </c:if>
                    <c:forEach var="collection" items="${user.getCollections()}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                ${collection.collectionName}
                            <button class="btn btn-primary addToCollectionButton" type="button" data-collectionid="${collection.id}">Add</button>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</section>

<c:import url="templates/alertbox.jsp" />
</body>
