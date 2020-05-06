<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${user != null}">
    <script>
        let username = "${user.username}";
    </script>
</c:if>