<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="browse">Movie Collector</a>
    <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
        <ul class="navbar-nav mr-auto mt-0">
            <li class="nav-item">
                <a class="nav-link" href="browse">Browse Movies</a>
            </li>
            <li class="nav-item">
            <c:if test="${user == null}">
                <a class="nav-link" href="profile">Login</a>
            </c:if>
            <c:if test="${user != null}">
                <a class="nav-link" href="profile">My Profile</a>
            </c:if>
            </li>
        </ul>

        <form class="form-inline my-2 my-lg-0 d-flex needs-validation" novalidate action="movie" method="get">
            <input class="form-control mr-2 flex-fill" id="searchBox" type="search" placeholder="Search" name="search" required>
            <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>