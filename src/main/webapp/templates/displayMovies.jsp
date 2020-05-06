<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="movie" items="${moviesToDisplay}">
    <div class="col-6 col-lg-3 my-2">
        <div class="card h-100">
            <a href="movie?id=${movie.imdbId}">
                <img class="card-img-top" src="${movie.image}" alt="${movie.title}">
            </a>
            <div class="card-body">
                <a href="movie?id=${movie.imdbId}" class="noLinkStyle"><h2 class="card-title h4">${movie.title}</h2></a>
                <c:if test="${collectionId == null}">
                    <button type="button" class="btn btn-primary w-100" onclick="displayCollectionModal('${movie.imdbId}')" data-toggle="modal" data-target="#collectionModal">
                        Add To Collection
                    </button>
                </c:if>
                <c:if test="${collectionId != null}">
                    <button type="button" class="btn btn-danger w-100" onclick="removeMovieFromCollection('${movie.imdbId}', '${collectionId}', this)">
                        Remove
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</c:forEach>