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
        <h1 class="col-sm-12 mt-2 text-center text-dark">
            ${collectionName}
        </h1>
    </div>
    <div class="row">
        <c:set var="moviesToDisplay" value="${movies}" scope="request"/>
        <c:import url="templates/displayMovies.jsp" />
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
                        <button class="btn btn-primary addToCollectionButton" data-collectionid="${collection.id}" type="button">Add</button>
                    </li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</section>

<c:import url="templates/alertbox.jsp" />

<c:import url="templates/formValidationScript.jsp" />
</body>
</html>