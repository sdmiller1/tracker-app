<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Browse Movies</title>
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

<main>
    <section id="movie-grouping">
        <div class="movie-list">

        </div>
    </section>
</main>

<div class="message error">
    <span id="messageText">Failed to add to Collection</span>
    <button onclick="closeMessage()"><i class="fa fa-close"></i></button>
</div>
</body>
</html>