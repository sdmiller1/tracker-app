<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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