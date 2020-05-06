<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <c:if test="${movie != null}">
        <title>${movie.title}</title>
    </c:if>
    <c:if test="${movie == null}">
        <title>Search For Movies</title>
    </c:if>

    <c:import url="templates/setJavascriptUsername.jsp" />

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
                <h2>${movie.title} <small>${movie.releaseYear}</small></h2>
                <p class="">
                        ${movie.plot}
                </p>
                <button type="button" class="btn btn-primary" onclick="displayCollectionModal('${movie.imdbId}')" data-toggle="modal" data-target="#collectionModal">
                    Add To Collection
                </button>
                <c:if test="${user != null}">
                <div class="rating mt-2" data-imdbid="${movie.imdbId}">
                    <i class="fa fa-star-o" onclick="rateMovie(this)" data-rating="1"></i>
                    <i class="fa fa-star-o" onclick="rateMovie(this)" data-rating="2"></i>
                    <i class="fa fa-star-o" onclick="rateMovie(this)" data-rating="3"></i>
                    <i class="fa fa-star-o" onclick="rateMovie(this)" data-rating="4"></i>
                    <i class="fa fa-star-o" onclick="rateMovie(this)" data-rating="5"></i>
                </div>
                </c:if>
            </div>
        </section>
    </c:if>

    <c:if test="${searchResults != null}">
        <div class="row">
            <h2 class="col-sm-6 text-dark mt-2">
                Search results for: ${searchTerm}
            </h2>
        </div>

        <div class="row">
            <c:if test="${searchResults.size() > 4}">
                <c:set var="moviesToDisplay" value="${searchResults.subList(0, 4)}" scope="request"/>
            </c:if>
            <c:if test="${searchResults.size() <= 4}">
                <c:set var="moviesToDisplay" value="${searchResults}" scope="request"/>
            </c:if>
            <c:import url="templates/displayMovies.jsp" />
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
        <c:if test="${movies.size() > 4}">
            <c:set var="moviesToDisplay" value="${movies.subList(0, 4)}" scope="request"/>
        </c:if>
        <c:if test="${movies.size() <= 4}">
            <c:set var="moviesToDisplay" value="${movies}" scope="request"/>
        </c:if>
        <c:import url="templates/displayMovies.jsp" />
    </div>
</main>

<c:import url="templates/collectionModal.jsp" />

<c:import url="templates/alertbox.jsp" />

<c:import url="templates/formValidationScript.jsp" />
</body>
