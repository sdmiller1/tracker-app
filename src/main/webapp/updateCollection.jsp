<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Add Movie With Your Voice</title>

    <c:import url="templates/setJavascriptUsername.jsp" />

    <c:import url="templates/head.jsp" />
    <script>
        if (annyang) {
            let commands = {
                'add *movie': getMoviesToDisplay
            }

            annyang.addCommands(commands);
            annyang.start();
        }
    </script>
</head>
<body>
<c:import url="templates/nav.jsp" />

<main class="container-md">
    <div class="row">
        <h1 class="col-sm-12 mt-2 text-center text-dark">
            Movies To Add
        </h1>
    </div>
    <div class="row" id="movieOutputLocation">
    </div>
</main>

<c:import url="templates/alertbox.jsp" />

<c:import url="templates/collectionModal.jsp" />
</body>
</html>